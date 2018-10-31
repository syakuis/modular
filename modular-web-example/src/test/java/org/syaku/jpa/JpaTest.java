package org.syaku.jpa;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 31/10/2018
 */

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaTest {

  @Autowired
  private ExampleRepository exampleRepository;

  @Autowired
  private ExampleService exampleService;

  @Test
  public void test() {
    exampleRepository.save(ExampleEntity.builder().name("good").value("good").build());
    assertEquals(exampleService.getExamples(), exampleRepository.findAll());
  }
}
