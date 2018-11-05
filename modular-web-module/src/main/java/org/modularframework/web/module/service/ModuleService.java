package org.modularframework.web.module.service;

import java.util.List;

import org.modularframework.context.view.ModuleOption;
import org.modularframework.web.module.domain.ModuleEntity;
import org.modularframework.web.module.domain.ModuleOptionEntity;
import org.modularframework.web.module.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 31/10/2018
 */
@Service
@Transactional
public class ModuleService {
  private ModuleRepository moduleRepository;

  @Autowired
  public void setModuleRepository(ModuleRepository moduleRepository) {
    this.moduleRepository = moduleRepository;
  }

  public List<ModuleEntity> getModules() {
    return moduleRepository.findAll();
  }

  public ModuleEntity getModule(String moduleIdx) {
    return moduleRepository.findOne(moduleIdx);
  }

  public ModuleEntity getModuleWithOption(String moduleId) {
    ModuleEntity result = moduleRepository.findOneByModuleId(moduleId);
    List<ModuleOptionEntity> moduleEntities = result.getModuleOptionEntities();
    return result;
  }

  public ModuleEntity saveModule(ModuleEntity moduleEntity) {
    return moduleRepository.save(moduleEntity);
  }
}
