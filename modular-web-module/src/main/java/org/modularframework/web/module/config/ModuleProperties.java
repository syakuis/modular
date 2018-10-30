package org.modularframework.web.module.config;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 21.
 */
@Configuration
@PropertySource("classpath:config/module.properties")
@ConfigurationProperties(prefix = "modular.web.module")
@Validated
@Data
public class ModuleProperties {
  @NotNull
  private String moduleName;
  @NotNull
  private String moduleIdx;
  @NotNull
  private boolean single;
}
