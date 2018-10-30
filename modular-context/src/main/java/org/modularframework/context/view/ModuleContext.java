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

  public Module getModuleByModuleIdx(String moduleIdx) {
    return this.store().getModuleByModuleIdx(moduleIdx);
  }

  /**
   * moduleId 와 연관되는 moduleIdx 찾아 반환한다. 주의!!! moduleIdx 외에 여러 값을 참조할때는 getModule() 사용한다.
   * @param moduleId moduleId
   * @return String moduleIdx
   * @see ModuleContext#getModule(String)
   */
  public String getModuleIdxByModuleId(String moduleId) {
    return this.store().getModuleIdx(moduleId);
  }

  /**
   * moduleIdx 와 연관되는 moduleId 찾아 반환한다. 주의!!! moduleId 외에 여러 값을 참조할때는 getModuleByModuleIdx() 사용한다.
   * @param moduleIdx moduleIdx
   * @return String moduleId
   * @see ModuleContext#getModuleByModuleIdx(String)
   */
  public String getModuleIdByModuleIdx(String moduleIdx) {
    return this.store().getModuleId(moduleIdx);
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

  /**
   * moduleIdx 와 연관되는 moduleName 찾아 반환한다. 주의!!! moduleName 외에 여러 값을 참조할때는 getModuleByModuleIdx() 사용한다.
   * @param moduleIdx moduleIdx
   * @return String moduleName
   * @see ModuleContext#getModuleByModuleIdx(String)
   */
  public String getModuleNameByModuleIdx(String moduleIdx) {
    return this.store().getModuleNameByModuleIdx(moduleIdx);
  }
}
