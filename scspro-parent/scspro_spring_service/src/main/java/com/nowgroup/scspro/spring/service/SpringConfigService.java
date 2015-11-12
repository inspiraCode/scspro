package com.nowgroup.scspro.spring.service;

import org.springframework.beans.factory.annotation.Value;

import com.nowgroup.scspro.service.SysConfigService;


public class SpringConfigService implements SysConfigService {
    
    @Value("${avatar.images}")
    private String avatarPath;
    
    @Override
    public String getAvatarPath() {
	return avatarPath;
    }

}
