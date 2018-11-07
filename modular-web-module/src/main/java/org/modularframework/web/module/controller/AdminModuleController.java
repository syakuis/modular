package org.modularframework.web.module.controller;

import org.modularframework.context.view.response.Done;
import org.modularframework.web.module.domain.ModuleEntity;
import org.modularframework.web.module.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
  public Done<Page<ModuleEntity>> pageList(
    @RequestParam(value = "page", defaultValue = "0", required = false) int page) {
    return Done.<Page<ModuleEntity>>builder().data(moduleService.getPageList(new PageRequest(page, 10))).build();
  }

  @GetMapping("/{moduleIdx}")
  public Done<ModuleEntity> view(@PathVariable("moduleIdx") String moduleIdx) {
    return Done.<ModuleEntity>builder().data(moduleService.getView(moduleIdx)).build();

  }

  @PostMapping
  public Done<ModuleEntity> save(@RequestBody ModuleEntity moduleEntity) {
    return Done.<ModuleEntity>builder().data(moduleService.save(moduleEntity)).build();
  }

  @DeleteMapping("{moduleIdx}")
  public Done<Void> save(@PathVariable String moduleIdx) {
    moduleService.delete(moduleIdx);
    return Done.<Void>builder().build();
  }
}
