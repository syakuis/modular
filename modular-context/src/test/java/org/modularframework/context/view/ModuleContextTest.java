package org.modularframework.context.view;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 30/10/2018
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modularframework.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(Application.class)
public class ModuleContextTest {

  @Configuration
  static class Config {
    class TestModuleInitializationService implements ModuleInitializationService {
      @Override
      public List<Module> getModules() {
        return Arrays.asList(
          Module.builder().moduleId("module").moduleName("module").moduleOptionMap(
              Module.putAll(ModuleOption.builder().name("idx").value("1").build())).build(),
          Module.builder().moduleId("test").moduleName("test").moduleOptionMap(
              Module.putAll(ModuleOption.builder().name("key").value("test").build())).build()
        );
      }
    }

    @Bean("moduleInitializationService")
    public ModuleInitializationService moduleInitializationService() {
      return new TestModuleInitializationService();
    }
  }

  @Autowired
  private CacheManager cacheManager;

  @Autowired
  private CacheModuleContextService cacheModuleContextService;

  @Autowired
  private ModuleContext moduleContext;

  @Test
  public void 서비스초기화데이터검사() {
    assertEquals(cacheModuleContextService.getModuleStore().values().size(), 2);
  }

  @Test
  public void 컨텍스트서비스캐시검사() {
    ModuleStore moduleStore = cacheModuleContextService.getModuleStore();
    assertEquals(moduleStore,
      cacheManager.getCache("module-context").get("store", ModuleStore.class));
    cacheModuleContextService.allClear();
    assertNull(cacheManager.getCache("module-context").get("store", ModuleStore.class));
  }

  @Test
  public void test() {
    ModuleStore moduleStore = cacheModuleContextService.getModuleStore();
    assertEquals(moduleContext.getModule("module"), moduleStore.getModule("module"));
    assertEquals(moduleContext.getModuleNameByModuleId("module"), moduleStore.getModuleNameByModuleId("module"));
  }
}
