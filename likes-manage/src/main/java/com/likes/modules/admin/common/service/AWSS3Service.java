package com.likes.modules.admin.common.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface AWSS3Service {

    /**
     * 获取分享路径 默认1天有效期
     *
     * @param bucketName
     * @param keyName
     * @return
     */
    String getVideoGeneratePresignedUrl(String bucketName, String keyName);

    /**
     * 生成唯一key s3
     *
     * @return
     */
    String getKeyCode();

    /**
     * 上传视频
     *
     * @param multipartFile
     * @return
     */
    Map<String, Object> trackMultipartUploadVideo(MultipartFile multipartFile, String preixfKey) throws Exception;

    /**
     * 上传视频封面
     *
     * @param multipartFile
     * @return
     */
    Map<String, Object> uploadCoverImages(MultipartFile multipartFile, String preixfKey) throws Exception;

    /**
     * 视频切图
     *
     * @param multipartFile
     * @param fileName
     * @param preixKey
     * @return
     * @throws Exception
     */
    Map<String, Object> uploadCutCoverImages(MultipartFile multipartFile, String fileName,
                                             String preixfKey) throws Exception;

    /**
     * 上传头像
     *
     * @param multipartFile
     * @return
     *//*
		Map<String, Object> uploadPhoto(MultipartFile multipartFile) throws Exception;*/

    /**
     * 上传广告图片
     *
     * @param multipartFile
     * @return
     * @throws Exception
     */
    Map<String, Object> uploadAdImages(MultipartFile multipartFile, String preixfKey) throws Exception;

    /**
     * 社区图片多上传
     *
     * @param multipartHttpServletRequest
     * @return
     */
    // Map<String, Object> manyArticlePicFileUpload(MultipartHttpServletRequest
    // multipartHttpServletRequest) throws Exception;
    Map<String, Object> manyArticlePicFileUpload(MultipartFile[] files, String prefixKey) throws Exception;

    String getVideoUrl(String keyName);

    /**
     * String内容上传
     *
     * @param content    String文本
     * @param preixfKey  前缀(可选)
     * @param fileSuffix 后缀(如.txt)
     */
    String uploadString(String content, String preixfKey, String fileSuffix) throws Exception;

    /**
     * zip上传(把String文本，压缩为zip，再上传)
     *
     * @param bytes
     * @param preixfKey
     * @param fileSuffix
     * @return
     * @throws Exception
     */
    String uploadZip(String content, String preixfKey, String fileSuffix) throws Exception;
}
