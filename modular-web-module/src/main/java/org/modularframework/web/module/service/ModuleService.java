package org.modularframework.web.module.service;

import java.util.List;

import org.modularframework.web.module.domain.ModuleEntity;
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

  public List<ModuleEntity> list() {
    return moduleRepository.findAll();
  }
}
