package com.likes.modules.admin.common.service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.likes.common.constant.PublicVariable;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.service.BaseServiceImpl;
import com.likes.common.util.CommonFunction;
import com.likes.common.util.DateUtils;
import com.likes.common.util.file.ZIPUtil;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.likes.modules.admin.common.service.AWSS3Service;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class AWSS3ServiceImpl extends BaseServiceImpl implements AWSS3Service {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String awsRegion;

    private String awsAccessKeyId;

    private String awsSecretSccessKey;

    private String awsBucketNameVideo;

    private String awsBucketNamePhoto;

    private String awsBucketNameFile;

    /**
     * 凭证
     *
     * @return
     */
    public AmazonS3 getS3() {
        // 区域
        Region region = Region.getRegion(Regions.fromName(awsRegion));
        AWSCredentials awsCredentials = new BasicAWSCredentials(awsAccessKeyId, awsSecretSccessKey);
        AmazonS3ClientBuilder builder = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials));
        builder.setRegion(region.getName());
        AmazonS3 s3 = builder.build();
        return s3;
    }

    @Override
    public String getVideoGeneratePresignedUrl(String bucketName, String keyName) {
        AmazonS3 s3 = getS3();
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, keyName)
                .withMethod(HttpMethod.GET).withExpiration(DateUtils.afterDate(new Date(), 0, 0, 1));
        URL url = s3.generatePresignedUrl(generatePresignedUrlRequest);
        // s3.shutdown();
        // System.out.println(IdleConnectionReaper.shutdown());
        s3.shutdown();
        String videourl = url.toString();
        if (RedisBusinessUtil.exists(PublicVariable.CDN_VIDEO_KEY)) {
            String vurl = RedisBusinessUtil.get(PublicVariable.CDN_VIDEO_KEY);
            String[] arr = videourl.split(keyName);
            return vurl + "/" + keyName + arr[1];
        } else {
            return videourl;
        }
    }

    public String getImgUrl(String keyName) {
        if (com.likes.common.util.StringUtils.isNotEmpty(keyName)) {
            if (keyName.startsWith("http")) {
                //不需要再组装url
                return keyName;
            }
            String url = RedisBusinessUtil.get(PublicVariable.CND_PIC_KEY);
            if (com.likes.common.util.StringUtils.isNotBlank(url)) {
                return url + "/" + keyName;
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public String getVideoUrl(String keyName) {
        if (org.apache.commons.lang.StringUtils.isNotEmpty(keyName)) {
            if (keyName.startsWith("http")) {
                //不需要再组装url
                return keyName;
            }
            return null;
        }
        return null;
    }


    private String getFilesUrl(String keyName) {
        if (org.apache.commons.lang.StringUtils.isNotEmpty(keyName)) {
            if (keyName.startsWith("http")) {
                //不需要再组装url
                return keyName;
            }
            return null;
        }
        return null;
    }

    /**
     * s3 生成对象 唯一的 key
     *
     * @return
     */
    @Override
    public String getKeyCode() {
        StringBuilder builder = new StringBuilder();
        builder.append(UUID.randomUUID());
        builder.append(CommonFunction.inviteCode().toLowerCase());
        return builder.toString().replaceAll("-", "");
    }

    @Override
    public Map<String, Object> trackMultipartUploadVideo(MultipartFile multipartFile, String preixfKey)
            throws Exception {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        Integer flag = 1;
        String fileName = multipartFile.getOriginalFilename();
        // 后缀 .flv
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
        String key = preixfKey + getKeyCode() + fileSuffix.toLowerCase();
        // aws s3 上传
        // Transfer
        AmazonS3 s3 = getS3();
        TransferManager tm = TransferManagerBuilder.standard().withS3Client(s3).build();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(multipartFile.getContentType());
        metadata.setContentLength(multipartFile.getSize());
        try {

            PutObjectRequest putObjectRequest = new PutObjectRequest(awsBucketNameVideo, key,
                    multipartFile.getInputStream(), metadata).withCannedAcl(CannedAccessControlList.PublicRead);
			/*
						// 监听在文件传输完毕后后有异常
						putObjectRequest.setGeneralProgressListener(new ProgressListener() {

							@Override
							public void progressChanged(ProgressEvent progressEvent) {
								logger.info("{}", "Transferred bytes: " + progressEvent.getBytesTransferred());
							}
						});*/

            Upload upload = tm.upload(putObjectRequest);
            // loop with Transfer.isDone()
            // XferMgrProgress.showTransferProgress(upload);
            upload.waitForCompletion();
        } catch (AmazonServiceException e) {
            flag = 0;
            logger.info("{}", "AmazonServiceException");
            e.printStackTrace();
            throw new BusinessException(StatusCode.LIVE_ERROR_1301.getCode(), "上传失败");
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            flag = 0;
            logger.info("{}", "SdkClientException");
            e.printStackTrace();
            throw new BusinessException(StatusCode.LIVE_ERROR_1301.getCode(), "上传失败");
        } finally {
            tm.shutdownNow();
            s3.shutdown();
        }

        logger.info("{}", "*******Completion**********");

        if (flag == 1) {
            // 获取视频播放路径
            //	GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(
            //		awsBucketNameVideo, key).withMethod(HttpMethod.GET);
            // .withExpiration(DateUtils.afterDate(new Date(), 0, 0, 1));
            //URL url = s3.generatePresignedUrl(generatePresignedUrlRequest);
            String url = getVideoUrl(key);
            dataMap.put("flag", 1);
            dataMap.put("keyName", url);
            dataMap.put("videoBofangUrl", url);
        } else {
            dataMap.put("flag", 0);
        }
        return dataMap;
    }

    @Override
    public Map<String, Object> uploadCoverImages(MultipartFile multipartFile, String preixfKey) throws Exception {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        Integer flag = 1;

        // 后缀 .flv
        String fileName = multipartFile.getOriginalFilename();
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
        String key = preixfKey + getKeyCode() + fileSuffix.toLowerCase();
        // String videocover = null;
        AmazonS3 s3 = getS3();
        TransferManager tm = TransferManagerBuilder.standard().withS3Client(s3).build();
        try {
            // aws s3 上传
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(multipartFile.getContentType());
            metadata.setContentLength(multipartFile.getSize());

            PutObjectRequest putObjectRequest = new PutObjectRequest(awsBucketNamePhoto, key,
                    multipartFile.getInputStream(), metadata).withCannedAcl(CannedAccessControlList.PublicRead);
            // 上传
            // s3.putObject(putObjectRequest);
            Upload upload = tm.upload(putObjectRequest);
            upload.waitForCompletion();
        } catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            flag = 0;
            logger.info("{}", "AmazonClientException");
            e.printStackTrace();
            throw new BusinessException(StatusCode.LIVE_ERROR_1301.getCode(), "上传失败");
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            flag = 0;
            logger.info("{}", "SdkClientException");
            e.printStackTrace();
            throw new BusinessException(StatusCode.LIVE_ERROR_1301.getCode(), "上传失败");
        } finally {
            tm.shutdownNow();
            s3.shutdown();
        }

        // 获取url
        // GeneratePresignedUrlRequest generatePresignedUrlRequest = new
        // GeneratePresignedUrlRequest(
        // awsBucketNamePhoto, key);
        // URL url = s3.generatePresignedUrl(generatePresignedUrlRequest);
        // String imageUrl = url.toString();
        // 图片设置的公开 所以可以直接获取url路径 从?截取
        // String[] arr = imageUrl.split("\\?");
        // logger.info("imageUrl: {}",imageUrl);
        // videocover = imageUrl;

        logger.info("{}", "*******Completion**********");
        if (flag == 1) {
            String url = getImgUrl(key);
            dataMap.put("flag", 1);
            dataMap.put("keyName", url);
            dataMap.put("coverUrl", url);
        } else {
            dataMap.put("flag", 0);
        }
        return dataMap;
    }

    @Override
    public Map<String, Object> uploadAdImages(MultipartFile multipartFile, String preixfKey) throws Exception {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        Integer flag = 1;

        // 后缀 .flv
        String fileName = multipartFile.getOriginalFilename();
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
        String key = preixfKey + getKeyCode() + fileSuffix.toLowerCase();
        AmazonS3 s3 = getS3();
        TransferManager tm = TransferManagerBuilder.standard().withS3Client(s3).build();
        try {
            // aws s3 上传
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(multipartFile.getContentType());
            metadata.setContentLength(multipartFile.getSize());

            PutObjectRequest putObjectRequest = new PutObjectRequest(awsBucketNamePhoto, key,
                    multipartFile.getInputStream(), metadata).withCannedAcl(CannedAccessControlList.PublicRead);
            // 上传
            // s3.putObject(putObjectRequest);
            Upload upload = tm.upload(putObjectRequest);
            upload.waitForCompletion();
        } catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            flag = 0;
            logger.info("{}", "AmazonClientException");
            e.printStackTrace();
            throw new BusinessException(StatusCode.LIVE_ERROR_1301.getCode(), "上传失败");
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            flag = 0;
            logger.info("{}", "SdkClientException");
            e.printStackTrace();
            throw new BusinessException(StatusCode.LIVE_ERROR_1301.getCode(), "上传失败");
        } finally {
            tm.shutdownNow();
            s3.shutdown();
        }


        logger.info("{}", "*******Completion**********");
        if (flag == 1) {
            String url = getImgUrl(key);
            dataMap.put("flag", 1);
            dataMap.put("keyName", url);
            dataMap.put("adUrl", url);
        } else {
            dataMap.put("flag", 0);
        }
        return dataMap;
    }

    @Override
    public Map<String, Object> manyArticlePicFileUpload(MultipartFile[] files, String preixfKey) throws Exception {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        List<String> keyNameList = new ArrayList<String>();
        List<String> avatarUrlList = new ArrayList<String>();
        List<String> faileList = new ArrayList<String>();
        AmazonS3 s3 = getS3();
        for (MultipartFile multipartFile : files) {
            Integer flag = 1;
            // 记录上传过程起始时的时间，用来计算上传时间
            long pre = System.currentTimeMillis();
            // 后缀 .flv
            String fileName = multipartFile.getOriginalFilename();
            String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
            String key = preixfKey + getKeyCode() + fileSuffix.toLowerCase();
            try {
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentType(multipartFile.getContentType());
                metadata.setContentLength(multipartFile.getSize());

                PutObjectRequest putObjectRequest = new PutObjectRequest(awsBucketNamePhoto, key,
                        multipartFile.getInputStream(), metadata).withCannedAcl(CannedAccessControlList.PublicRead);
                // 上传
                s3.putObject(putObjectRequest);
            } catch (AmazonServiceException e) {
                // The call was transmitted successfully, but Amazon S3 couldn't process
                // it, so it returned an error response.
                flag = 0;
                logger.info("{}", "AmazonClientException");
                s3.shutdown();
                e.printStackTrace();
                throw new BusinessException(StatusCode.LIVE_ERROR_1301.getCode(), "上传失败");
            } catch (SdkClientException e) {
                // Amazon S3 couldn't be contacted for a response, or the client
                // couldn't parse the response from Amazon S3.
                flag = 0;
                logger.info("{}", "SdkClientException");
                s3.shutdown();
                e.printStackTrace();
                throw new BusinessException(StatusCode.LIVE_ERROR_1301.getCode(), "上传失败");
            } finally {
                //s3.shutdown();
            }

            if (flag == 1) {
                String url = getImgUrl(key);
                keyNameList.add(url);
                avatarUrlList.add(url);
            } else {
                faileList.add(multipartFile.getOriginalFilename());
            }

            long finaltime = System.currentTimeMillis();
            logger.info("上传费时：{}", (finaltime - pre));
        }

        // 设置返回
        dataMap.put("keyNameList", keyNameList);
        dataMap.put("avatarUrlList", avatarUrlList);
        dataMap.put("faileList", faileList);
        s3.shutdown();
        return dataMap;
    }

    @Override
    public Map<String, Object> uploadCutCoverImages(MultipartFile multipartFile, String fileName, String preixfKey)
            throws Exception {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        Integer flag = 1;

        // 后缀 .flv
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
        String key = preixfKey + getKeyCode() + fileSuffix.toLowerCase();
        // String videocover = null;
        AmazonS3 s3 = getS3();
        TransferManager tm = TransferManagerBuilder.standard().withS3Client(s3).build();
        try {
            // aws s3 上传
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(multipartFile.getContentType());
            metadata.setContentLength(multipartFile.getSize());

            PutObjectRequest putObjectRequest = new PutObjectRequest(awsBucketNamePhoto, key,
                    multipartFile.getInputStream(), metadata).withCannedAcl(CannedAccessControlList.PublicRead);
            // 上传
            // s3.putObject(putObjectRequest);
            Upload upload = tm.upload(putObjectRequest);
            upload.waitForCompletion();
        } catch (AmazonServiceException e) {
            flag = 0;
            logger.info("{}", "AmazonClientException");
            e.printStackTrace();
            throw new BusinessException(StatusCode.LIVE_ERROR_1301.getCode(), "上传失败");
        } catch (SdkClientException e) {
            flag = 0;
            logger.info("{}", "SdkClientException");
            e.printStackTrace();
            throw new BusinessException(StatusCode.LIVE_ERROR_1301.getCode(), "上传失败");
        } finally {
            tm.shutdownNow();
            s3.shutdown();
        }

        logger.info("{}", "*******Completion**********");
        if (flag == 1) {
            String url = getImgUrl(key);
            dataMap.put("flag", 1);
            dataMap.put("keyName", url);
            dataMap.put("coverUrl", url);
        } else {
            dataMap.put("flag", 0);
        }
        return dataMap;
    }

    @Override
    public String uploadString(String content, String preixfKey, String fileSuffix) throws Exception {
        if (StringUtils.isNotBlank(content)) {
            String contentType = "text/plain";
            byte[] bytes = content.getBytes(StandardCharsets.UTF_8.name());
            return this.uploadBytes(bytes, preixfKey, fileSuffix, contentType);
        } else {
            logger.info("{}上传文件内容为空", getClass().getName());
            return null;
        }

    }

    @Override
    public String uploadZip(String content, String preixfKey, String fileSuffix) throws Exception {
        if (StringUtils.isNotBlank(content)) {
            byte[] bytes = ZIPUtil.zip(content.getBytes(StandardCharsets.UTF_8.name()));
            String contentType = "text/plain";
            return this.uploadBytes(bytes, preixfKey, fileSuffix, contentType);
        } else {
            logger.info("{}上传压缩文件内容为空", getClass().getName());
            return null;
        }
    }

    private String uploadBytes(byte[] bytes, String preixfKey, String fileSuffix, String contentType) throws Exception {
//        String key = preixfKey + getKeyCode() + fileSuffix;
        String key = preixfKey + fileSuffix;
        logger.info(key);
        AmazonS3 s3 = getS3();

        String uploadUrl = null;
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(contentType);
            metadata.setContentLength(bytes.length);
            InputStream targetStream = new ByteArrayInputStream(bytes);

            PutObjectRequest putObjectRequest = new PutObjectRequest(awsBucketNameFile, key,
                    targetStream, metadata).withCannedAcl(CannedAccessControlList.PublicRead);
            // 上传
            s3.putObject(putObjectRequest);

            uploadUrl = this.getFilesUrl(key);
            logger.info("{}文件上传成功,文件目录{}", getClass().getName(), uploadUrl);
        } catch (AmazonServiceException e) {
            logger.info("{}", "AmazonClientException", e);
            throw new BusinessException(StatusCode.LIVE_ERROR_1303.getCode(), "上传失败");
        } catch (SdkClientException e) {
            logger.info("{}", "SdkClientException", e);
            throw new BusinessException(StatusCode.LIVE_ERROR_1301.getCode(), "上传失败");
        } finally {
            s3.shutdown();
        }
        return uploadUrl;
    }
}
