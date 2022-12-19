package com.likes.modules.admin.file.controller;

import com.likes.common.annotation.AllowAccess;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.service.uploadFile.MinioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/uploadFiles")
public class FileController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private MinioService minioService;

    @PostMapping
    @ApiOperation(value = "文件上传")
//    @AllowAccess
    public ResultInfo<String> uploadFile(
            @ApiParam("文件") @RequestParam(value = "file") MultipartFile file,
            @ApiParam("存储桶名称(非必须，微服务有单独默认存储桶)") @RequestParam(value = "bucketName", required = false) String bucketName
    ) {
        String path = null;
        ResultInfo response = ResultInfo.ok();
        try {
            path = minioService.putObject(file, bucketName);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.uploadFiles 文件上传出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("文件上传失败");
            logger.error("{}.guploadFiles 文件上传处理出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        return ResultInfo.ok(path);
    }


}
