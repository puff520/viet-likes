package com.likes.modules.admin.common.controller;

import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.likes.common.annotation.AllowAccess;
import com.likes.common.constant.Constants;
import com.likes.common.enums.StatusCode;
import com.likes.common.enums.UniqueCodeEnum;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.HelpManual;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.request.BaseRequest;
import com.likes.common.mybatis.entity.MemBaseinfo;
import com.likes.common.mybatis.entity.MemHotsearch;
import com.likes.common.mybatis.entity.SysBusparameter;
import com.likes.common.mybatis.mapper.HelpManualMapper;
import com.likes.common.mybatis.mapperext.sys.SysBusparameterMapperExt;
import com.likes.common.service.code.UniqueCodeService;
import com.likes.common.service.member.MemBaseinfoService;
import com.likes.common.service.sys.SysBusParamService;
import com.likes.common.service.sys.SysFeedbackService;
import com.likes.common.service.uploadFile.UplaodFileSevice;
import com.likes.common.util.CaptchaUtils;
import com.likes.common.util.CaptchaUtils.ComplexLevel;
import com.likes.common.util.VerifyCode2Util;
import com.likes.common.util.http.HttpRequest;
import com.likes.common.util.redis.RedisBaseUtil;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.likes.modules.admin.common.service.CommonService;
import com.github.binarywang.utils.qrcode.MatrixToImageWriter;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.List;

@Controller
public class CommonController {

    private final Logger logger = LogManager.getLogger(CommonController.class);
    @Resource
    private CommonService commonService;
    @Resource
    private UniqueCodeService uniqueCodeService;
    @Resource
    private MemBaseinfoService memBaseinfoService;
    @Resource
    private SysFeedbackService sysFeedbackService;

    @Resource
    private HelpManualMapper manualMapper;

    @Resource
    private UplaodFileSevice uplaodFileSevice;


    @PostMapping("/heartbeat")
    @ResponseBody
    public ResultInfo heartbeat() {
        return ResultInfo.ok();
    }

    @RequestMapping(name = "获取业务参数", value = "/param/getChild", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo index(String busparamcode) {
        ResultInfo response = ResultInfo.ok();
        if (org.apache.commons.lang.StringUtils.isEmpty(busparamcode)) {
            throw new BusinessException(StatusCode.LIVE_ERROR_997.getCode(), "参数为空");
        }
        List<SysBusparameter> list = this.commonService.getParamList(busparamcode);
        response.setData(list);
        return response;
    }

    @AllowAccess
    @ResponseBody
    @RequestMapping(name = "获取热词列表", value = "getHotSearch", method = RequestMethod.GET)
    public ResultInfo getHotSearch(MemHotsearch memHotsearch) {
        ResultInfo response = ResultInfo.ok();
        response.setData(commonService.getHotSearch(memHotsearch));
        return response;
    }

//    /**
//     * @param width
//     * @param height
//     * @param response
//     * @param session
//     * @author abu
//     * <p>
//     * Description:<br>
//     * 获取图形验证码
//     */
//    @AllowAccess
//    @RequestMapping(name = "获取图形验证码", value = "/getCaptchaImage", method = RequestMethod.GET)
//    public void getCaptchaImage(Integer width, Integer height, HttpServletResponse response, HttpSession session) {
//        if (width == null) {
//            width = 150;
//        }
//        if (height == null) {
//            height = 50;
//        }
//        Object[] obj = CaptchaUtils.getCaptchaImage(width, height, 4, 30, 10, 100, true, false, ComplexLevel.HARD);
//        session.setAttribute(Constants.ADMIN_VERCODE, obj[1]);
//        try {
//            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
//            ByteArrayOutputStream os = new ByteArrayOutputStream();
//            ImageIO.write((BufferedImage) obj[0], "jpg", os);
//            InputStream is = new ByteArrayInputStream(os.toByteArray());
//            byte[] buffer = new byte[1024];
//            int len;
//            while ((len = is.read(buffer)) > -1) {
//                outputStream.write(buffer, 0, len);
//            }
//            outputStream.flush();
//            is.close();
//            outputStream.close();
//        } catch (Exception e) {
//            logger.error(e);
//        }
//    }



    @AllowAccess
    @RequestMapping(name = "获取图形验证码", value = "/getCaptchaImage", method = RequestMethod.GET)
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) {
        VerifyCode2Util verifyCode2Util = new VerifyCode2Util();
        verifyCode2Util.generateCode();
        String codeText = verifyCode2Util.getText();
        String codeKey = IdUtil.fastSimpleUUID();
        RedisBaseUtil.set("imageCode:" + codeKey, codeText, 60 * 5L);
        request.getSession().setAttribute("AppConst.LOGIN_CODE_KEY", codeKey);
        verifyCode2Util.writeCodeToRespone(response);
    }


    @RequestMapping(name = "生成二维码", value = "qrcode", method = RequestMethod.POST)
    public void qrcode(String url, Integer width, Integer height, Boolean down, HttpServletResponse response) throws WriterException, IOException {
        if (StringUtils.isEmpty(url) || width < 0 || height < 0) {
            try (PrintWriter pw = response.getWriter()) {
                pw.print("参数错误");
            }
            return;
        }
        if (down != null && down) {
            response.setHeader("Content-Disposition", "attachment; filename=二维码");
            response.setContentType("application/octet-stream;charset=UTF-8");
        }
        try (ServletOutputStream stream = response.getOutputStream()) {
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            // BitMatrix bitMatrix = new MultiFormatWriter().encode(url,
            // BarcodeFormat.QR_CODE, width, height, hints);
            // 生成无边框的二维码
            ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.L;
            int quietZone = 0;
            if (hints != null) {
                if (hints.containsKey(EncodeHintType.ERROR_CORRECTION)) {
                    errorCorrectionLevel = ErrorCorrectionLevel.valueOf(hints.get(EncodeHintType.ERROR_CORRECTION));
                }
                if (hints.containsKey(EncodeHintType.MARGIN)) {
                    quietZone = Integer.parseInt(hints.get(EncodeHintType.MARGIN));
                }
            }
            QRCode code = Encoder.encode(url, errorCorrectionLevel, hints);
            ByteMatrix input = code.getMatrix();
            int inputWidth = input.getWidth();
            int inputHeight = input.getHeight();
            int qrWidth = inputWidth + (quietZone * 2);
            int qrHeight = inputHeight + (quietZone * 2);
            int outputWidth = Math.max(width, qrWidth);
            int outputHeight = Math.max(height, qrHeight);

            int multiple = Math.min(outputWidth / qrWidth, outputHeight / qrHeight);
            // Padding includes both the quiet zone and the extra white pixels
            // to accommodate the requested
            // dimensions. For example, if input is 25x25 the QR will be 33x33
            // including the quiet zone.
            // If the requested size is 200x160, the multiple will be 4, for a
            // QR of 132x132. These will
            // handle all the padding from 100x100 (the actual QR) up to
            // 200x160.
            int leftPadding = (outputWidth - (inputWidth * multiple)) / 2;
            int topPadding = (outputHeight - (inputHeight * multiple)) / 2;
            BitMatrix output = new BitMatrix(outputWidth, outputHeight);
            for (int inputY = 0, outputY = topPadding; inputY < inputHeight; inputY++, outputY += multiple) {
                // Write the contents of this row of the barcode
                for (int inputX = 0, outputX = leftPadding; inputX < inputWidth; inputX++, outputX += multiple) {
                    if (input.get(inputX, inputY) == 1) {
                        output.setRegion(outputX, outputY, multiple, multiple);
                    }
                }
            }
            MatrixToImageWriter.writeToStream(output, "JPEG", stream);
            stream.flush();
        }
    }


    @AllowAccess
    @RequestMapping("/test/code/generator/{type}")
    @ResponseBody
    public ResultInfo generatorUniqueCodes(@PathVariable("type") String type) {
        UniqueCodeEnum uniqueCodeEnum = UniqueCodeEnum.valueOfName(type);
        try {
            uniqueCodeService.generatorUniqueCodes(uniqueCodeEnum);
            return ResultInfo.ok();
        } catch (Exception e) {
            logger.error("generatorUniqueCodes occur error", e);
            return ResultInfo.error();
        }
    }

    @AllowAccess
    @RequestMapping("/test/mem/uniqueId")
    @ResponseBody
    public ResultInfo handMemUniqueId() {
        try {
            long start = System.currentTimeMillis();
            memBaseinfoService.handMemUniqueId();
            long end = System.currentTimeMillis();
            logger.info("handMemUniqueId success. times:{}", end - start);
            return ResultInfo.ok();
        } catch (Exception e) {
            logger.error("handMemUniqueId occur error", e);
            return ResultInfo.error();
        }
    }

    /**
     * 历史数据处理：
     * 提现总次数,首次提现金额,最大提现金额,充值总次数,首次充值金额,最大充值金额
     */

    @GetMapping("/test/mem/handMemWithdrawalAndPayInfo")
    @ResponseBody
    public ResultInfo handMemWithdrawalAndPayInfo() {
        try {
            long start = System.currentTimeMillis();
            memBaseinfoService.handMemWithdrawalAndPayInfo();
            long end = System.currentTimeMillis();
            logger.info("handMemWithdrawalAndPayInfo success. times:{}", end - start);
            return ResultInfo.ok();
        } catch (Exception e) {
            logger.error("handMemWithdrawalAndPayInfo occur error", e);
            return ResultInfo.error();
        }
    }

    /**
     * 历史数据处理：
     */
    @AllowAccess
    @GetMapping("/test/feedback")
    @ResponseBody
    public ResultInfo handFeedback() {
        try {
            long start = System.currentTimeMillis();
            sysFeedbackService.handFeedback();
            long end = System.currentTimeMillis();
            logger.info("handFeedback success. times:{}", end - start);
            return ResultInfo.ok();
        } catch (Exception e) {
            logger.error("handFeedback occur error", e);
            return ResultInfo.error();
        }
    }


    @Resource
    private SysBusparameterMapperExt sysBusparameterMapperExt;

    @AllowAccess
    @RequestMapping(name = "业务参数获取", value = "/param/getChildBypcode", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getChildBypcode(String pcode) {
        ResultInfo response = ResultInfo.ok();
        SysBusparameter list = sysBusparameterMapperExt.selectByBusparamcode(pcode);
        response.setData(list);
        return response;
    }

    @RequestMapping(name = "业务参数获取", value = "/param/getXinyong", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getXinyong() {
        ResultInfo response = ResultInfo.ok();
        String pcode = "dianzai";
        List<SysBusparameter> list = this.commonService.getParamList(pcode);
        response.setData(list);
        return response;
    }

    @Resource
    private SysBusParamService sysBusParamService;

    @ResponseBody
    @RequestMapping(name = "获取公告", value = "/param/getNote", method = RequestMethod.GET)
    public ResultInfo getNovel() {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(this.sysBusParamService.getNote());
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("失败:{}", e.getMessage());
        }
        logger.info("/updateNovel耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @AllowAccess
    @RequestMapping(name = "分页帮助文档", value = "helpManual/list", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo Paging(BaseRequest req, String keyWord) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            PageHelper.startPage(req.getPageNo(), req.getPageSize());
            List<HelpManual> list = manualMapper.getList(keyWord);
            PageInfo<HelpManual> pageInfo = new PageInfo<>(list);
            response.setData(pageInfo);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("helpManual/list:{}", e.getMessage());
        }
        logger.info("helpManual/list耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @ApiOperation(value = "文件上传", notes = "文件上传接口")
    @RequestMapping(name = "文件上传", value = "/uploadFiel", method = RequestMethod.POST)
    public ResultInfo uploadFiel(@ApiParam(value = "上传的文件", required = true) @RequestParam(value = "files", required = true) MultipartFile[] files, @ApiParam(value = "业务模块-key(系统参数-配置对应文件位置)", required = false) @RequestParam(value = "moduleKey", required = false) String moduleKey, @ApiParam(value = "文件类型-0：图片、1：视频、2：其他文件", required = true) @RequestParam(value = "type", required = true) Integer type) {
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo<>();
        if (files == null || files.length == 0) {
            logger.error("{}.testUploadFiel,失败信息:{}", getClass().getName(), "上传文件接口 文件流为空");
        }
        try {
            response = ResultInfo.ok(uplaodFileSevice.uploadFile(files, moduleKey, type));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.testUploadFiel,失败信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("上传文件出错");
            logger.error("{}.testUploadFiel,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/testUploadFiel{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }


}
