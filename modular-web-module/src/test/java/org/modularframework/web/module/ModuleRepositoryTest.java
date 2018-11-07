package org.modularframework.web.module;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 01/11/2018
 */

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Commit
public class ModuleRepositoryTest {
  @PersistenceContext
  private EntityManager entityManager;
  @Autowired
  private ModuleRepository moduleRepository;
  private String moduleId;
  private String moduleIdx;

  private void initOne() {
    this.moduleId = UUID.randomUUID().toString();
    ModuleEntity moduleEntity = ModuleEntity.builder()
      .moduleId(moduleId).moduleName(moduleId)
      .moduleOptionEntities(Arrays.asList(
        ModuleOptionEntity.builder().name("kr").value("한국").build(),
        ModuleOptionEntity.builder().name("us").build()
      )).build();

    moduleRepository.save(moduleEntity);
    this.moduleIdx = moduleEntity.getModuleIdx();

    entityManager.flush();
    entityManager.clear();
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



/*
  @Test
  public void 모듈_목록() {
    // Given
    // When
    List<ModuleEntity> moduleEntities = moduleRepository.findAll();
    ModuleEntity moduleEntity = moduleRepository.findOneByModuleId(moduleId);
    List<ModuleOptionEntity> moduleOptionEntities = moduleEntity.getModuleOptionEntities();

    // Then
    assertEquals(moduleEntities.size(), 1);
    assertEquals(moduleOptionEntities.size(), 2);
  }

  @Test
  public void 모듈_저장() {
    // Given & When
    String moduleId = UUID.randomUUID().toString();
    ModuleEntity moduleEntity = ModuleEntity.builder()
      .moduleId(moduleId).moduleName(moduleId).build();
    moduleRepository.save(moduleEntity);

    // Then
    assertNotNull(moduleEntity.getModuleIdx());
    assertNull(moduleEntity.getModuleOptionEntities());
  }

  @Test
  public void 모듈과옵션_저장() {
    // When
    ModuleEntity target = moduleRepository.findOne(moduleIdx);
    ModuleEntity get = moduleRepository.getOne(moduleIdx);

    // Then
    assertNotNull(moduleIdx);
    assertEquals(moduleEntity, target);
  }

  @Test
  public void 모듈_수정() {
    // Given
    ModuleEntity moduleEntity = initData();
    assertNotNull(moduleEntity.getModuleIdx());

    // When
    moduleEntity.setBrowserTitle("수정");

    // Then
    assertEquals(moduleRepository.findOne(moduleEntity.getModuleIdx()).getBrowserTitle(), "수정");
  }

  @Test
  public void 모듈옵션_삭제() {
    // Given
    ModuleEntity moduleEntity = initData();
    assertNotNull(moduleEntity.getModuleIdx());

    // When
    List<ModuleOptionEntity> moduleOptionEntities = Lists.newArrayList(moduleEntity.getModuleOptionEntities());
    assertEquals(moduleOptionEntities.size(), 2);
    moduleOptionEntities.remove(moduleOptionEntities.get(1));
    moduleEntity.setModuleOptionEntities(moduleOptionEntities);

    // Then
    assertEquals(moduleRepository.findOne(moduleEntity.getModuleIdx()).getModuleOptionEntities().size(), 1);
  }*/
}
