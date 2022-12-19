package com.likes.common.service.code;

import com.likes.common.constant.Constants;
import com.likes.common.enums.SysParameterEnum;
import com.likes.common.enums.UniqueCodeEnum;
import com.likes.common.mybatis.entity.SysParameter;
import com.likes.common.mybatis.entity.UserUniqueCode;
import com.likes.common.mybatis.mapperext.UniqueCodeMapperExt;
import com.likes.common.service.sys.SysParamService;
import com.likes.common.util.CollectionUtil;
import com.likes.common.util.RandomUtil;
import com.likes.common.util.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ClassName: UniqueCodeServiceImpl
 * Description: 描述
 *
 * @author hai
 * @since JDK 1.8
 * date: 2020/7/17 16:16
 */
@Service
public class UniqueCodeServiceImpl implements UniqueCodeService {

    private static final Logger logger = LoggerFactory.getLogger(UniqueCodeServiceImpl.class);
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private UniqueCodeMapperExt uniqueCodeMapperExt;
    @Resource
    private RedissonClient redissonClient;
    @Resource
    private SysParamService sysParamService;

    @Override
    public String getUniqueCode(UniqueCodeEnum uniqueCodeEnum) {
        //获取随机码
        String code = popRandomCode(uniqueCodeEnum);
        if (StringUtils.isEmpty(code)) {
            //加载随机码
            if (!loadUniqueCode(uniqueCodeEnum)) {
                //生成随机码
                generatorUniqueCodes(uniqueCodeEnum);
                //再次加载
                loadUniqueCode(uniqueCodeEnum);
            }
            //再次获取
            code = popRandomCode(uniqueCodeEnum);
        }
        return code;
    }

    @Override
    public String getMemUniqueId() {
        return getUniqueCode(UniqueCodeEnum.USER_UNIQUE);
    }

    @Override
    public String getMemInviteCode() {
        return getUniqueCode(UniqueCodeEnum.USER_INVITE);
    }

    private String popRandomCode(UniqueCodeEnum uniqueCodeEnum) {
        String key = uniqueCodeEnum.getName() + "_cache";
        String code = (String) redisTemplate.opsForList().leftPop(key);
        logger.info("unique-code-log popRandomCode:{} {}", key, code);
        return code;
    }

    private void pushRandomCode(UniqueCodeEnum uniqueCodeEnum, Set<String> codes) {
        if (null == uniqueCodeEnum || CollectionUtil.isEmpty(codes)) {
            return;
        }
        String key = uniqueCodeEnum.getName() + "_cache";
        //防止重放入
        redisTemplate.delete(key);
        redisTemplate.opsForList().rightPushAll(key, codes);
        logger.info("unique-code-log pushRandomCode:{} size {}", key, codes.size());
    }

    private boolean hasCodes(UniqueCodeEnum uniqueCodeEnum) {
        String key = uniqueCodeEnum.getName() + "_cache";
        Long size = redisTemplate.opsForList().size(key);
        logger.info("unique-code-log hasCodes:{} {}", key, size);
        return null != size && size > 0;
    }

    @Override
    public boolean loadUniqueCode(UniqueCodeEnum uniqueCodeEnum) {
        RLock lock = redissonClient.getLock(uniqueCodeEnum.getName() + "_load");
        boolean result = false;
        int size = 0;
        try {
            if (lock.tryLock()) {
                //再次判断，防止多次加载
                if (hasCodes(uniqueCodeEnum)) {
                    return true;
                }
                int loadCodeSize = getLoadCodeSize();
                List<UserUniqueCode> uniqueCodeList = uniqueCodeMapperExt.loadUniqueCode(uniqueCodeEnum.getName(), loadCodeSize);
                if (CollectionUtil.isNotEmpty(uniqueCodeList)) {
                    result = true;
                    size = uniqueCodeList.size();
                    Set<String> codes = uniqueCodeList.stream().map(item -> item.getCode()).collect(Collectors.toSet());
                    //放redis
                    pushRandomCode(uniqueCodeEnum, codes);
                    //更新状态
                    updateCodeStatus(uniqueCodeList.get(uniqueCodeList.size() - 1).getId(), uniqueCodeEnum);
                }
            }
        } catch (Exception e) {
            logger.error("unique-code-log loadUniqueCode ocuur error. table:{}", uniqueCodeEnum.getName(), e);
        } finally {
            lock.unlock();
            logger.info("unique-code-log loadUniqueCode table:{} size:{}, result:{}", uniqueCodeEnum.getName(), size, result);
        }
        return result;
    }

    @Override
    public Set<String> loadAllUniqueCode(UniqueCodeEnum uniqueCodeEnum) {
        long start = System.currentTimeMillis();
        Set<String> codes = uniqueCodeMapperExt.loadAllUniqueCode(uniqueCodeEnum.getName());
        codes = null == codes ? new HashSet<>() : codes;
        long end = System.currentTimeMillis();
        logger.info("unique-code-log loadAllUniqueCode size:{}, times:{}", codes.size(), end - start);
        return codes;
    }

    @Override
    public boolean generatorUniqueCodes(UniqueCodeEnum uniqueCodeEnum) {
        RLock lock = redissonClient.getLock(uniqueCodeEnum.getName() + "_generator");
        long start = System.currentTimeMillis();
        boolean result = false;
        int alreadyCodesSize = 0;
        Set<String> alreadyCodes = new HashSet<>();
        try {
            if (lock.tryLock()) {
                alreadyCodes = loadAllUniqueCode(uniqueCodeEnum);
                Set<String> codes = new HashSet<>();
                int batchCount = 0;
                alreadyCodesSize = alreadyCodes.size();

                int genCodeSize = getGenCodeSize();
                int batchCodeSize = getBatchCodeSize();
                while (alreadyCodes.size() < genCodeSize) {
                    String code = RandomUtil.genRandomString(uniqueCodeEnum.getLength());
                    if (alreadyCodes.contains(code)) {
                        continue;
                    }
                    codes.add(code);
                    alreadyCodes.add(code);
                    if (++batchCount >= batchCodeSize) {
                        uniqueCodeMapperExt.batchInsertCodes(uniqueCodeEnum.getName(), codes);
                        codes.clear();
                        batchCount = 0;
                        logger.info("unique-code-log batchInsertCodes curr size {}", alreadyCodes.size());
                    }
                }
                result = true;
            }
        } catch (Exception e) {
            logger.error("unique-code-log generatorUniqueCodes ocuur error. table:{}", uniqueCodeEnum.getName(), e);
        } finally {
            lock.unlock();
            long end = System.currentTimeMillis();
            logger.info("unique-code-log generatorUniqueCodes size:{}, alreadyCodes.size:{}, times:{}, result:{}", alreadyCodes.size() - alreadyCodesSize, alreadyCodesSize, end - start, result);
        }
        return result;
    }

    @Override
    public boolean updateCodeStatus(int maxRecordId, UniqueCodeEnum uniqueCodeEnum) {
        int effects = uniqueCodeMapperExt.updateCodeStatus(uniqueCodeEnum.getName(), maxRecordId, 1);
        logger.info("unique-code-log updateCodeStatus:{} maxId:{} rows:{}", uniqueCodeEnum.getName(), maxRecordId, effects);
        return effects > 0;
    }

    private int getLoadCodeSize() {
        SysParameter sysParameter = sysParamService.getByCode(SysParameterEnum.UNIQUE_CODE_LOAD_SIZE);
        if (null == sysParameter || StringUtils.isEmpty(sysParameter.getSysparamvalue())) {
            return Constants.UNIQUE_CODE_LOAD_SIZE;
        }
        try {
            return Integer.parseInt(sysParameter.getSysparamvalue());
        } catch (Exception e) {
            return Constants.UNIQUE_CODE_LOAD_SIZE;
        }
    }

    private int getBatchCodeSize() {
        SysParameter sysParameter = sysParamService.getByCode(SysParameterEnum.UNIQUE_CODE_BATCH_SIZE);
        if (null == sysParameter || StringUtils.isEmpty(sysParameter.getSysparamvalue())) {
            return Constants.UNIQUE_CODE_BATCH_SIZE;
        }
        try {
            return Integer.parseInt(sysParameter.getSysparamvalue());
        } catch (Exception e) {
            return Constants.UNIQUE_CODE_BATCH_SIZE;
        }
    }

    private int getGenCodeSize() {
        SysParameter sysParameter = sysParamService.getByCode(SysParameterEnum.UNIQUE_CODE_GEN_SIZE);
        if (null == sysParameter || StringUtils.isEmpty(sysParameter.getSysparamvalue())) {
            return Constants.UNIQUE_CODE_GEN_SIZE;
        }
        try {
            return Integer.parseInt(sysParameter.getSysparamvalue());
        } catch (Exception e) {
            return Constants.UNIQUE_CODE_GEN_SIZE;
        }
    }

}
