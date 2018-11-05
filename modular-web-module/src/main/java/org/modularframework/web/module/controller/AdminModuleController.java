package org.modularframework.web.module.controller;

import java.util.List;

import org.modularframework.context.view.response.Done;
import org.modularframework.web.module.domain.ModuleEntity;
import org.modularframework.web.module.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 31/10/2018
 */
@RestController
@RequestMapping("/admin/module")
public class AdminModuleController {
  @Autowired
  private ModuleService moduleService;

  @GetMapping
  public Done<List<ModuleEntity>> list() {
    return Done.<List<ModuleEntity>>builder().data(moduleService.getModules()).build();
  }

  @PostMapping
  public Done<ModuleEntity> save(@RequestBody ModuleEntity moduleEntity) {
    return Done.<ModuleEntity>builder().data(moduleService.saveModule(moduleEntity)).build();
  }

  @PutMapping(name = "/{moduleId}")
  public Done<ModuleEntity> save(@PathVariable String moduleId, @RequestBody ModuleEntity moduleEntity) {
    return Done.<ModuleEntity>builder().data(moduleService.saveModule(moduleEntity)).build();
  }
}
