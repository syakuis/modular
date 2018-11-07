package org.modularframework.web.module.service;

import org.modularframework.web.module.domain.ModuleEntity;
import org.modularframework.web.module.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

  public Page<ModuleEntity> getPageList(Pageable pageable) {
    return moduleRepository.findAll(pageable);
  }

  public ModuleEntity getView(String moduleIdx) {
    return moduleRepository.findOne(moduleIdx);
  }

  public ModuleEntity save(ModuleEntity moduleEntity) {
    return moduleRepository.save(moduleEntity);
  }

  public void delete(String moduleIdx) {
    moduleRepository.delete(moduleIdx);
  }
}
