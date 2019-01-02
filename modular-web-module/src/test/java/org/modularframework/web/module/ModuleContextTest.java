package org.modularframework.web.module;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 30/10/2018
 */

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modularframework.context.view.ModuleContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModuleContextTest {
  @Autowired
  private ModuleContext moduleContext;

  @Test
  public void test() {
//    assertEquals(moduleContext.getModule("test").getModuleId(), "test");
  }
}
