package org.modularframework.context.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 30/10/2018
 */
@Service
@CacheConfig(cacheNames = "module-context")
public class ModuleContextService {
  private ModuleInitializationService moduleInitializationService;

  @Autowired
  public void setModuleInitializationService(ModuleInitializationService moduleInitializationService) {
    this.moduleInitializationService = moduleInitializationService;
  }

  @Cacheable(key = "'store'")
  public ModuleStore getModuleStore() {
    return new ModuleStore(moduleInitializationService.getModules());
  }

  @CacheEvict(allEntries = true)
  public void allClear() {

  }
}
