package com.likes.common.util.redis;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.util.CollectionUtil;
import com.likes.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: RedisBaseUtil
 * Description: 描述
 *
 * @author hai
 * @since JDK 1.8
 * date: 2020/8/24 23:21
 */
public class RedisBaseUtil {

    private static final Logger logger = LoggerFactory.getLogger(RedisBaseUtil.class);
    public volatile static RedisTemplate redisTemplate;

    public static void init(RedisTemplate redisTemplate) {
        if (null == RedisBaseUtil.redisTemplate && null != redisTemplate) {
            RedisBaseUtil.redisTemplate = redisTemplate;
            logger.info("RedisBaseUtil init:{}", redisTemplate);
        }
    }

    /////////////////////////////////////////通用基础方法/////////////////////////////////////////

    public static <T> T get(Object key) {
        if (null == key || StringUtils.isEmpty(key.toString())) {
            return null;
        }
        return (T) redisTemplate.opsForValue().get(key);
    }

    public static void set(Object key, Object value) {
        set(key, value, null, null);
    }

    public static void set(Object key, Object value, Long expireTime) {
        set(key, value, expireTime, TimeUnit.SECONDS);
    }

    public static void set(Object key, Object value, Long expireTime, TimeUnit unit) {
        if (null == key || StringUtils.isEmpty(key.toString()) || null == value) {
            return;
        }
        if (null != expireTime && expireTime > 0) {
            unit = null == unit ? TimeUnit.SECONDS : unit;
            redisTemplate.opsForValue().set(key, value, expireTime, unit);
        } else {
            redisTemplate.opsForValue().set(key, value);
        }
    }

    public static void delete(String key) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        redisTemplate.delete(key);
        logger.info("delete:{} success.", key);
    }

    public static void delete(String... keys) {
        if (null == keys || keys.length == 0) {
            return;
        }
        Set set = new HashSet();
        for (String k : keys) {
            if (null == k || "".equals(k.trim())) {
                continue;
            }
            set.add(k);
        }
        if (set.size() > 0) {
            redisTemplate.delete(set);
            logger.info("delete:{} success.", JSONObject.toJSONString(keys));
        }
    }

    public static void delete(Collection collection) {
        if (null != collection && collection.size() > 0) {
            redisTemplate.delete(collection);
        }
    }

    /**
     * 删除指定key的缓存，多个key间以逗号分隔
     *
     * @param keys
     */
    public static void deleteByKeys(String keys) {
        if (StringUtils.isEmpty(keys)) {
            return;
        }
        List<String> idList = StringUtils.splitStringList(keys);
        redisTemplate.delete(idList);
        logger.info("deleteByKeys:{} success.", keys);
    }

    /**
     * 模糊匹配清除缓存
     *
     * @param key
     */
    public static void deleteFuzzyMatchCache(String key) {
        if (StringUtils.isBlank(key)) {
            return;
        }
        Set indistinctKeys = redisTemplate.keys(key + "*");
        if (CollectionUtil.isNotEmpty(indistinctKeys)) {
            redisTemplate.delete(indistinctKeys);
        }
    }

    public static Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    public static void setExpire(String key, Long second) {
        setExpire(key, second, TimeUnit.SECONDS);
    }

    public static void setExpire(String key, Long second, TimeUnit unit) {
        try {
            if (StringUtils.isEmpty(key)) {
                return;
            }
            unit = null == unit ? TimeUnit.SECONDS : unit;
            redisTemplate.expire(key, second, unit);
        } catch (Exception e) {
            logger.error("setExpireTime:{} expire:{} occur error.", key, second, e);
        }
    }

    public static boolean exists(String key) {
        if (StringUtils.isEmpty(key)) {
            return false;
        }
        return redisTemplate.hasKey(key);
    }

    /////////////////////////////////////////redis bitmap 相关方法 start/////////////////////////////////////////

    /**
     * 将指定offset偏移量的值设置为0；
     *
     * @param key    bitmap结构的key
     * @param offset 指定的偏移量。
     * @return 若偏移位上的值为1，那么返回true。
     */
    public static Boolean getBit(String key, Long offset) {
        return redisTemplate.opsForValue().getBit(key, offset);
    }

    /**
     * 将指定offset偏移量的值设置为1；
     *
     * @param key    bitmap结构的key
     * @param offset 指定的偏移量。
     * @param value  true：即该位设置为1，否则设置为0
     * @return 返回设置该value之前的值。
     */
    public static Boolean setBit(String key, Long offset, boolean value) {
        return redisTemplate.opsForValue().setBit(key, offset, value);
    }

    /**
     * 统计字符串指定位上被设置为1的bit数
     *
     * @param key 键
     * @return long
     */
    public static Long bitCount(String key) {
        Long result = (Long) redisTemplate.execute(new RedisCallback<Long>() {
            @Nullable
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.bitCount(key.getBytes());
            }
        });
        return Optional.ofNullable(result).orElse(0L);
    }

    /**
     * 统计字符串指定位上被设置为1的bit数
     *
     * @param key   键
     * @param start 开始位置  注意对应byte的位置,是bit位置*8
     * @param end   结束位置
     * @return long
     */
    public static Long bitCount(String key, long start, long end) {
        Long result = (Long) redisTemplate.execute(
                new RedisCallback<Long>() {
                    @Nullable
                    @Override
                    public Long doInRedis(RedisConnection connection) throws DataAccessException {
                        return connection.bitCount(key.getBytes(), start, end);
                    }
                }
        );
        return Optional.ofNullable(result).orElse(0L);
    }

    /**
     * 对符合指定格式的key值进行未操作
     *
     * @param op      操作类型：与、或、异或、否
     * @param destKey 存放结果的键
     * @param pattern key格式
     * @return Long
     */
    public static Long bitOp(RedisStringCommands.BitOperation op, String destKey, String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        return bitOp(op, destKey, keys);
    }

    /**
     * 对符合指定格式的key值进行未操作
     *
     * @param op      操作类型：与、或、异或、否
     * @param destKey 存放结果的键
     * @param keys    key 集合
     * @return Long
     */
    public static Long bitOp(RedisStringCommands.BitOperation op, String destKey, Collection<String> keys) {
        keys = null == keys ? Collections.EMPTY_LIST : keys;
        int size = keys.size();
        if (size == 0) {
            return 0L;
        }
        int index = 0;
        byte[][] bytes = new byte[size][];
        for (String key : keys) {
            bytes[index++] = key.getBytes();
        }
        return (Long) redisTemplate.execute((RedisCallback<Long>) con -> con.bitOp(op, destKey.getBytes(), bytes));
    }
    /////////////////////////////////////////redis bitmap 相关方法 end/////////////////////////////////////////

    /**
     * 递增 此时value值必须为int类型 否则报错
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @return
     */
    public static long increment(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     * @return
     */
    public static long decrement(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().decrement(key, delta);
    }

    //================================Map=================================

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public static Object hGet(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public static Map<Object, Object> hMGet(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public static boolean hSet(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            logger.error("hSet for key:{},map:{} occur error:{}", key, JSONObject.toJSONString(map), e);
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public static boolean hMSet(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                setExpire(key, time);
            }
            return true;
        } catch (Exception e) {
            logger.error("hMSet for key:{},map:{},time:{} occur error:{}", key, JSONObject.toJSONString(map), time, e);
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public static boolean hSet(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            logger.error("hSet for key:{},item:{},value:{} occur error:{}", key, item, value, e);
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public static boolean hSet(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                setExpire(key, time);
            }
            return true;
        } catch (Exception e) {
            logger.error("hSet for key:{},item:{},value:{},time:{} occur error:{}", key, item, value, time, e);
            return false;
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public static void hDelete(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public static boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return
     */
    public static double hIncrement(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return
     */
    public static double hDecrement(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    //============================set=============================

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */
    public static Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            logger.error("sGet for key:{} occur error:{}", key, e);
            return null;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public static boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            logger.error("sHasKey for key:{},value:{} occur error:{}", key, value, e);
            return false;
        }
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public static long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            logger.error("sSet for key:{},values:{} occur error:{}", key, JSONObject.toJSONString(values), e);
            return 0;
        }
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public static long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0) {
                setExpire(key, time);
            }
            return count;
        } catch (Exception e) {
            logger.error("sSetAndTime for key:{},time:{},values:{} occur error:{}", key, time, JSONObject.toJSONString(values), e);
            return 0;
        }
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return
     */
    public static long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            logger.error("sGetSetSize for key:{}, occur error:{}", key, e);
            return 0;
        }
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public static long setRemove(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().remove(key, values);
        } catch (Exception e) {
            logger.error("setRemove for key:{},values:{} occur error:{}", key, JSONObject.toJSONString(values), e);
            return 0;
        }
    }
    //===============================list=================================

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束  0 到 -1代表所有值
     * @return
     */
    public static List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            logger.error("lGet for key:{},start:{},end:{} occur error:{}", key, start, end, e);
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return
     */
    public static long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            logger.error("lGetListSize for key:{} occur error:{}", key, e);
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public static Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            logger.error("lGetIndex for key:{},index:{} occur error:{}", key, index, e);
            return null;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public static boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            logger.error("lSet for key:{},value:{} occur error:{}", key, value, e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public static boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                setExpire(key, time);
            }
            return true;
        } catch (Exception e) {
            logger.error("lSet for key:{},time:{},value:{} occur error:{}", key, time, value, e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public static boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            logger.error("lSet for key:{},value:{} occur error:{}", key, JSONObject.toJSONString(value), e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public static boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                setExpire(key, time);
            }
            return true;
        } catch (Exception e) {
            logger.error("lSet for key:{},time:{},value:{} occur error:{}", key, time, JSONObject.toJSONString(value), e);
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     */
    public static boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            logger.error("lUpdateIndex for key:{},index:{},value:{} occur error:{}", key, index, value, e);
            return false;
        }
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public static long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            logger.error("lRemove for key:{},count:{},value:{} occur error:{}", key, count, value, e);
            return 0;
        }
    }



}
