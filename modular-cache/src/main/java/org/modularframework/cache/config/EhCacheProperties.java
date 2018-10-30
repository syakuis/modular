package org.modularframework.cache.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 10.
 */
@Configuration
@PropertySource("classpath:config/cache.properties")
@ConfigurationProperties(prefix = "modular.cache.ehcache")
@Data
public class EhCacheProperties {
    private boolean enable;
    private String config;
    private String cacheConfig;
    private String charset;
}
