package org.modularframework.context.view;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 30/10/2018
 */
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheModuleContextServiceTest {
  @Autowired
  private CacheModuleContextService cacheModuleContextService;

  @Test
  public void test() {
    cacheModuleContextService.getModuleStore();
  }
}
