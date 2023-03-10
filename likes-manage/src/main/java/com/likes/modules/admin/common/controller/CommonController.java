package com.likes.modules.admin.common.controller;

import com.likes.common.BaseController;
import com.likes.common.constant.Constants;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.dto.OperatorDO;
import com.likes.common.mybatis.entity.SysBusparameter;
import com.likes.common.service.uploadFile.UplaodFileSevice;
import com.likes.common.util.CaptchaUtils;
import com.likes.common.util.CaptchaUtils.ComplexLevel;
import com.likes.modules.admin.common.service.CommonService;
import com.github.binarywang.utils.qrcode.MatrixToImageWriter;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;
import io.swagger.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
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
@Api(value = "????????????")
public class CommonController extends BaseController {

    private final Logger logger = LogManager.getLogger(CommonController.class);

    @Resource
    private CommonService commonService;
    @Resource
    private UplaodFileSevice uplaodFileSevice;

    /**
     * @return
     * @author Description:<br>
     * ??????
     */
    @RequestMapping(name = "????????????", value = "/timeout", method = RequestMethod.GET)
    public String timeout() {
        return "/common/timeout";
    }

    /**
     * @return
     * @author ????????????
     */
    @RequestMapping(name = "????????????", value = "/audit", method = RequestMethod.GET)
    public String audit() {
        return "/common/403";
    }

    @RequestMapping(name = "??????????????????", value = "/param/getChildBypcode", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo index(String pcode) {
        ResultInfo response = ResultInfo.ok();

        List<SysBusparameter> list = this.commonService.getParamList(pcode);
        response.setData(list);
        return response;
    }

    /**
     * @param width
     * @param height
     * @param response
     * @param session
     * @author abu
     * <p>
     * Description:<br>
     * ?????????????????????
     */
    @RequestMapping(name = "?????????????????????", value = "/getCaptchaImage", method = RequestMethod.POST)
    public void getCaptchaImage(Integer width, Integer height, HttpServletResponse response, HttpSession session) {
        if (width == null) {
            width = 150;
        }
        if (height == null) {
            height = 50;
        }
        Object[] obj = CaptchaUtils.getCaptchaImage(width, height, 4, 30, 10, 100, true, false, ComplexLevel.HARD);
        session.setAttribute(Constants.ADMIN_VERCODE, obj[1]);
        try {
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write((BufferedImage) obj[0], "jpg", os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) > -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();
            is.close();
            outputStream.close();
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @RequestMapping(name = "???????????????", value = "qrcode", method = RequestMethod.POST)
    public void qrcode(String url, Integer width, Integer height, Boolean down, HttpServletResponse response)
            throws WriterException, IOException {
        if (StringUtils.isEmpty(url) || width < 0 || height < 0) {
            try (PrintWriter pw = response.getWriter()) {
                pw.print("????????????");
            }
            return;
        }
        if (down != null && down) {
            response.setHeader("Content-Disposition", "attachment; filename=?????????");
            response.setContentType("application/octet-stream;charset=UTF-8");
        }
        try (ServletOutputStream stream = response.getOutputStream()) {
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            // BitMatrix bitMatrix = new MultiFormatWriter().encode(url,
            // BarcodeFormat.QR_CODE, width, height, hints);
            // ???????????????????????????
            ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.L;
            int quietZone = 0;
            if (hints != null) {
                if (hints.containsKey(EncodeHintType.ERROR_CORRECTION)) {
                    errorCorrectionLevel = ErrorCorrectionLevel
                            .valueOf(hints.get(EncodeHintType.ERROR_CORRECTION));
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

    @RequestMapping(name = "??????APP????????????", value = "/getOperatorList", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getOperatorList() {
        ResultInfo response = ResultInfo.ok();
        List<OperatorDO> list = this.commonService.getOperatorList();
        response.setData(list);
        return response;
    }

    @RequestMapping(name = "??????????????????", value = "/getSiteNotice", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getSiteNotice(PageBounds pageBounds) {
        ResultInfo response = ResultInfo.ok();
        try {
            response = this.commonService.getSiteNotice(pageBounds);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("????????????????????????:{}", e.getMessage());
        }
        return response;
    }

    @ResponseBody
    @ApiOperation(value = "????????????", notes = "??????????????????")
    @RequestMapping(name = "????????????", value = "/uploadFiel", method = RequestMethod.POST)
    public ResultInfo uploadFiel(
            @ApiParam(value = "???????????????", required = true)@RequestParam(value = "files",required = true) MultipartFile[]  files,
            @ApiParam(value = "????????????-key(????????????-????????????????????????)", required = false)@RequestParam(value = "moduleKey",required = false) String moduleKey,
            @ApiParam(value = "????????????-0????????????1????????????2???????????????", required = true)@RequestParam(value = "type",required = true) Integer type
    ) {
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo<>();
        if(files==null || files.length==0){
            logger.error("{}.testUploadFiel,????????????:{}", getClass().getName(), "?????????????????? ???????????????");
        }
        try {
            response = ResultInfo.ok(uplaodFileSevice.uploadFile(files, moduleKey,type));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.testUploadFiel,????????????:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("??????????????????");
            logger.error("{}.testUploadFiel,????????????:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/testUploadFiel{}?????????", (System.currentTimeMillis() - start));
        return response;
    }

}
