package org.modularframework.context.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 29.
 */
@Component
public final class ModuleContext {
  private CacheModuleContextService moduleContextService;

  @Autowired
  public void setModuleContextService(CacheModuleContextService moduleContextService) {
    this.moduleContextService = moduleContextService;
  }

  private ModuleStore store() {
    return this.moduleContextService.getModuleStore();
  }

  public List<Module> getModules() {
    return this.store().values();
  }

  public Module getModule(String moduleId) {
    return this.store().getModule(moduleId);
  }

  /**
   * moduleId 와 연관되는 moduleName 찾아 반환한다. 주의!!! moduleName 외에 여러 값을 참조할때는 getModule() 사용한다.
   * @param moduleId moduleId
   * @return String moduleName
   * @see ModuleContext#getModule(String)
   */
  public String getModuleNameByModuleId(String moduleId) {
    return this.store().getModuleNameByModuleId(moduleId);
  }
}
