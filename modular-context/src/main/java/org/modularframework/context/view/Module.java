package org.modularframework.context.view;

import java.io.Serializable;
import java.util.Map;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 30/10/2018
 */
@Builder
@Data
@Setter(AccessLevel.NONE)
public final class Module implements Serializable {
  private final String moduleIdx;
  private final String moduleId;
  private final String moduleName;
  private String skin;
  /**
   * Config 의 테마를 우선 사용할지 여부
   */
  private boolean onlyUseTheme;
  private String browserTitle;
  private Map<String, ModuleOption> moduleOptions;
  private String layoutIdx;
}
