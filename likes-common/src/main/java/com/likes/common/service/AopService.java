package com.likes.common.service;


public interface AopService {

    void saveMemberOnline(String source, String uid);

    void saveMemberYouke(String agent, String source, String uid);

    void saveDeviceIdYouke(String deviceInfo);

}
