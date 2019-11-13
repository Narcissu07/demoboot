package com.unicorn.demoboot.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yangjie on 2017/10/13.
 */
@Configuration
public class BizConfig {
    @Value("${mockUrl}")
    public String mockUrl;

}
