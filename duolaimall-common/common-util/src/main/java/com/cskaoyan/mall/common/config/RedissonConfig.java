package com.cskaoyan.mall.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * redisson配置信息
 */
@Data
@Configuration
@ConfigurationProperties("spring.redis")
public class RedissonConfig {

//    private String host;
//
//    private String addresses;
//
//    private String password;
//
//    private String port;
//
//    private int timeout = 3000;
//    private int connectionPoolSize = 64;
//    private int connectionMinimumIdleSize=10;
//    private int pingConnectionInterval = 60000;
//    private static String PROTOCAL = "redis://";
//
//    /**
//     * 自动装配
//     *
//     */
//    @Bean
//    RedissonClient redissonSingle() {
//        Config config = new Config();
//        if(StringUtils.isEmpty(host)){
//            throw new RuntimeException("host is  empty");
//        }
//        SingleServerConfig serverConfig = config.useSingleServer()
//                //redis://127.0.0.1:7181
//                .setAddress(PROTOCAL + this.host + ":" + port)
//                .setTimeout(this.timeout)
//                .setPingConnectionInterval(pingConnectionInterval)
//                .setConnectionPoolSize(this.connectionPoolSize)
//                .setConnectionMinimumIdleSize(this.connectionMinimumIdleSize)
//                ;
//        if(!StringUtils.isEmpty(this.password)) {
//            serverConfig.setPassword(this.password);
//        }
//
//        // 设置序列化
//        config.setCodec(new JsonJacksonCodec());
//
//        return Redisson.create(config);
//    }
}
