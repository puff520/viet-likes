package com.likes.modules.admin.common.service;

import com.likes.common.model.dto.FileDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


public interface AWSS3Service {

    /**
     * 获取分享路径
     * 默认1天有效期
     *
     * @param bucketName
     * @param keyName
     * @return
     */
    String getVideoGeneratePresignedUrl(String bucketName, String keyName);

    /**
     * 上次头像
     *
     * @param multipartFile
     * @return
     */
    Map<String, Object> uploadHeadimg(MultipartFile multipartFile, String preixfKey) throws Exception;

    /**
     * 获取唯一key
     *
     * @return
     */
    String getKeyCode();

    /**
     * 多图上传
     *
     * @param files
     * @return
     */
    Map<String, Object> manyArticlePicFileUpload(MultipartFile[] files, String preixfKey) throws Exception;

    /**
     * 上传视频
     *
     * @param multipartFile
     * @param preixfKey
     * @return
     * @throws Exception
     */
    Map<String, Object> trackMultipartUploadVideo(MultipartFile multipartFile, String preixfKey) throws Exception;

    /**
     * 多图上传返回
     *
     * @param files
     * @param
     * @return
     */
    List<FileDO> manyArticlePicFileUploadTWO(MultipartFile[] files, String preixfKey) throws Exception;

    String getVideoUrl(String keyName);


}
