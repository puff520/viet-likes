package com.likes.common.model.dto.bas;

import com.alibaba.fastjson.JSON;
import com.likes.common.mybatis.entity.BasVideo;

import java.lang.reflect.InvocationTargetException;

public class BasVideoDO extends BasVideo {
    //视频封面
    private String coverUrl;

    //视频播放路径
    private String videoBofangUrl;

    /**
     * 用户昵称
     */
    private String createusername;

    /**
     * 用户昵称，和createusername一样，android要求加，android使用
     */
    private String videoauthor;

    public String getVideoauthor() {
        return videoauthor;
    }

    public void setVideoauthor(String videoauthor) {
        this.videoauthor = videoauthor;
    }

    public String getCreateusername() {
        return createusername;
    }

    public void setCreateusername(String createusername) {
        this.createusername = createusername;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getVideoBofangUrl() {
        return videoBofangUrl;
    }

    public void setVideoBofangUrl(String videoBofangUrl) {
        this.videoBofangUrl = videoBofangUrl;
    }

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        BasVideo basVideo = new BasVideo();
        basVideo.setAccno("2121");
        BasVideoDO basVideoDO = new BasVideoDO();
        //BeanUtils.copyProperties(basVideo, basVideoDO);
        //org.apache.commons.beanutils.BeanUtils.copyProperties(basVideoDO,basVideo);
        //BeanUtils.copyPropertiesExcludeNullAndBlank(basVideo, basVideoDO);
        System.out.println(JSON.toJSONString(basVideoDO));
    }
}
