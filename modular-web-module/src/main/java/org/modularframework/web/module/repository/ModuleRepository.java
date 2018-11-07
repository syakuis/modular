package org.modularframework.web.module.repository;


import org.modularframework.web.module.domain.ModuleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 24.
 */
public interface ModuleRepository extends Repository<ModuleEntity, String> {
  Page<ModuleEntity> findAll(Pageable pageable);
  ModuleEntity findOne(String moduleIdx);
  ModuleEntity save(ModuleEntity moduleEntity);
  void delete(String moduleIdx);
}
