package com.nowgroup.scspro.spring.service;

import org.springframework.beans.factory.annotation.Value;

import com.nowgroup.scspro.service.SysConfigService;


public class SpringConfigService implements SysConfigService {
    
    @Value("${avatar.images}")
    private String avatarPath;
    
    @Value("${material.images}")
    private String materialImagePath;
    
    @Override
    public String getAvatarPath() {
	return avatarPath;
    }
    
    @Override
    public String getMaterialImagePath() {
	return materialImagePath;
    }

}
