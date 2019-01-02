package org.modularframework.web.module;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 01/11/2018
 */

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modularframework.web.module.domain.ModuleEntity;
import org.modularframework.web.module.domain.ModuleOptionEntity;
import org.modularframework.web.module.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ListModuleRepositoryTest {
  @Autowired
  private ModuleRepository moduleRepository;

  @Before
  public void setUp() {

  }

  @After
  public void exit() {

  }

  private void initMany(int row) {
    for (int i = 0; i < row; i++) {
      String moduleId = UUID.randomUUID().toString();
      ModuleEntity moduleEntity = ModuleEntity.builder()
      .moduleId(moduleId).moduleName(moduleId)
      .moduleOptionEntities(Arrays.asList(
        ModuleOptionEntity.builder().name("kr").value("한국").build(),
        ModuleOptionEntity.builder().name("us").build()
      )).build();

      moduleRepository.save(moduleEntity);
    }
  }

  @Test
  public void 모듈_목록_페이징() {
    int row = 15;
    initMany(row);

    Page<ModuleEntity> page = moduleRepository.findAll(new PageRequest(0, 10));
    assertEquals(row, page.getTotalElements());
    assertEquals(page.getNumberOfElements(), 10);
    assertEquals(page.getTotalPages(), 2);

    Page<ModuleEntity> page2 = moduleRepository.findAll(new PageRequest(1, 10));
    assertEquals(page2.getNumberOfElements(), 5);
  }
}
