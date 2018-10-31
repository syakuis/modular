package org.modularframework.web.module.controller;

import java.util.List;

import org.modularframework.context.view.response.Done;
import org.modularframework.web.module.domain.ModuleEntity;
import org.modularframework.web.module.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    return Done.<List<ModuleEntity>>builder().data(moduleService.list()).build();
  }
}
