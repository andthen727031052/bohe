package com.hsl.bohe.common.syscount;


import com.hsl.bohe.common.util.JedisUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JedisConfig {
    @Bean
    public JedisUtil createJ(){
        //return new JedisUtil(SystemCon.REDISHOST,SystemCon.REDISPORT,SystemCon.REDISPASS);
        return new JedisUtil(SystemCon.REDISHOST,SystemCon.REDISPORT);
    }
}
