package org.modularframework.web.module.repository;


import java.util.List;

import org.modularframework.web.module.domain.ModuleEntity;
import org.springframework.data.repository.Repository;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 24.
 */
public interface ModuleRepository extends Repository<ModuleEntity, String> {
    List<ModuleEntity> findAll();
    ModuleEntity findOne(String id);
    ModuleEntity findOneByModuleId(String moduleId);
    ModuleEntity save(ModuleEntity moduleEntity);
    void removeById(String id);
}
