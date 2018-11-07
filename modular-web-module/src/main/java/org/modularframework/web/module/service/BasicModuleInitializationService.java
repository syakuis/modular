package org.modularframework.web.module.service;

import java.util.List;
import java.util.Map;

import org.modularframework.common.data.enums.YesOrNo;
import org.modularframework.context.view.Module;
import org.modularframework.context.view.ModuleInitializationService;
import org.modularframework.context.view.ModuleOption;
import org.modularframework.web.module.domain.ModuleEntity;
import org.modularframework.web.module.domain.ModuleOptionEntity;
import org.modularframework.web.module.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 29.
 */
@Service("moduleInitializationService")
@Transactional
public class BasicModuleInitializationService implements ModuleInitializationService {
  private ModuleRepository moduleRepository;

  @Autowired
  public void setModuleRepository(ModuleRepository moduleRepository) {
  this.moduleRepository = moduleRepository;
  }
  
  private Module transform(ModuleEntity moduleEntity) {
    Map<String, ModuleOption> moduleOptions = Maps.newHashMap(Maps.transformValues(
      Maps.uniqueIndex(moduleEntity.getModuleOptionEntities(), new Function<ModuleOptionEntity, String>() {
        @Override
        public String apply(ModuleOptionEntity input) {
          return input.getName();
        }
      }),
      new Function<ModuleOptionEntity, ModuleOption>() {
        @Override
        public ModuleOption apply(ModuleOptionEntity entity) {
          return ModuleOption.builder()
            .name(entity.getName())
            .value(entity.getValue())
            .title(entity.getTitle()).build();
        }
      }));

    return Module.builder()
      .moduleId(moduleEntity.getModuleId())
      .moduleName(moduleEntity.getModuleName())
      .skin(moduleEntity.getSkin())
      .onlyUseTheme(moduleEntity.getOnlyUseTheme().equals(YesOrNo.Y))
      .browserTitle(moduleEntity.getBrowserTitle())
      .moduleOptionMap(moduleOptions)
      .layoutIdx(moduleEntity.getLayoutIdx())
      .build();
  }

  @Override
  public List<Module> getModules() {
//    List<ModuleEntity> moduleEntities = moduleRepository.findAll();
//    return Lists.newArrayList(Lists.transform(moduleEntities, new Function<ModuleEntity, Module>() {
//      @Override
//      public Module apply(ModuleEntity entity) {
//        return transform(entity);
//      }
//    }));

    return null;
  }
}
