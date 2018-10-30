package org.modularframework.context.view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
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
  private final String moduleId;
  private final String moduleName;
  private String skin;
  /**
   * Config 의 테마를 우선 사용할지 여부
   */
  private boolean onlyUseTheme;
  private String browserTitle;
  private Map<String, ModuleOption> moduleOptionMap;
  private String layoutIdx;

  public static Map<String, ModuleOption> putAll(ModuleOption ...moduleOptions) {
    Map<String, ModuleOption> result = new HashMap<>();
    for (ModuleOption moduleOption : moduleOptions) {
      result.put(moduleOption.getName(), moduleOption);
    }

    return result;
  }

  public ModuleOption getModuleOption(String name) {
    return this.moduleOptionMap.get(name);
  }

  public List<ModuleOption> getModuleOptions() {
    return Lists.newArrayList(this.moduleOptionMap.values());
  }
}
