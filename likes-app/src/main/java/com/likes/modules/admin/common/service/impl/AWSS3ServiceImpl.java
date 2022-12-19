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
import com.likes.common.model.dto.FileDO;
import com.likes.common.service.BaseServiceImpl;
import com.likes.common.util.CommonFunction;
import com.likes.common.util.DateUtils;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.likes.modules.admin.common.service.AWSS3Service;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class AWSS3ServiceImpl extends BaseServiceImpl implements AWSS3Service {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String awsRegion;

    private String awsAccessKey;

    private String awsSecretSccessKey;

    private String awsBucketNameVideo;

    private String awsBucketNamePhoto;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 凭证
     *
     * @return
     */
    public AmazonS3 getS3() {
        //东京区域
        Region region = Region.getRegion(Regions.fromName(awsRegion));
        AWSCredentials awsCredentials = new BasicAWSCredentials(awsAccessKey, awsSecretSccessKey);
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
                .withMethod(HttpMethod.GET)
                .withExpiration(DateUtils.afterDate(new Date(), 0, 0, 1));
        URL url = s3.generatePresignedUrl(generatePresignedUrlRequest);
        s3.shutdown();
        String videourl = url.toString();
        if (redisTemplate.hasKey(PublicVariable.CDN_VIDEO_KEY)) {
            String vurl = (String) redisTemplate.opsForValue().get(PublicVariable.CDN_VIDEO_KEY);
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
        if (StringUtils.isNotEmpty(keyName)) {
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
    public Map<String, Object> uploadHeadimg(MultipartFile multipartFile, String preixfKey) throws Exception {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        Integer flag = 1;

        // 后缀 .flv
        String fileName = multipartFile.getOriginalFilename();
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
        String key = preixfKey + getKeyCode() + fileSuffix.toLowerCase();
        AmazonS3 s3 = getS3();
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
            logger.info("{}", "AmazonClientException", e);
            throw new BusinessException(StatusCode.LIVE_ERROR_1303.getCode(), "上传失败");
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            flag = 0;
            logger.info("{}", "SdkClientException", e);
            throw new BusinessException(StatusCode.LIVE_ERROR_1301.getCode(), "上传失败");
        } finally {
            s3.shutdown();
        }

        if (flag == 1) {
            String url = getImgUrl(key);
            dataMap.put("flag", 1);
            dataMap.put("keyName", url);
            dataMap.put("avatarUrl", url);
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

        dataMap.put("keyNameList", keyNameList);
        dataMap.put("avatarUrlList", avatarUrlList);
        dataMap.put("faileList", faileList);
        s3.shutdown();
        return dataMap;
    }

    @Override
    public List<FileDO> manyArticlePicFileUploadTWO(MultipartFile[] files, String preixfKey) throws Exception {
        List<FileDO> list = new ArrayList<FileDO>();
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

            FileDO fileDO = new FileDO();
            fileDO.setFilename(multipartFile.getOriginalFilename());
            if (flag == 1) {
                String url = getImgUrl(key);
                fileDO.setFilekey(url);
                fileDO.setIssuccess(0);
                fileDO.setFilekeyurl(url);
            } else {
                fileDO.setIssuccess(9);
            }

            list.add(fileDO);
            long finaltime = System.currentTimeMillis();
            logger.info("上传费时：{}", (finaltime - pre));
        }
        s3.shutdown();
        return list;
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
			/*GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(
					awsBucketNameVideo, key).withMethod(HttpMethod.GET);
			URL url = s3.generatePresignedUrl(generatePresignedUrlRequest);*/
            String url = getVideoUrl(key);
            dataMap.put("flag", 1);
            dataMap.put("keyName", url);
            //dataMap.put("videoBofangUrl", url.toString());
            dataMap.put("videoBofangUrl", url);
        } else {
            dataMap.put("flag", 0);
        }
        return dataMap;
    }

}
