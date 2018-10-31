package org.modularframework.data.jpa.autoconfigure;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * todo ConditionalOnProperty 검사에서 PropertySource 값을 읽지 못함. application.properties 설정해줘야 한다. 좀 더 깔끔하게 처리할 수 있게 개선한다. syakuis/ainframe#20
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 7.
 */
@Configuration
@EntityScan("org.modularframework")
@EnableJpaRepositories("org.modularframework")
@PropertySource("classpath:config/jpa.properties")
public class JpaDataAutoConfiguration {
}
