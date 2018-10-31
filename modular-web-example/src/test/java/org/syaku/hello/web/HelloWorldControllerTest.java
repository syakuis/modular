package org.syaku.hello.web;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 31/10/2018
 */

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloWorldControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void test() throws Exception {
    this.mockMvc.perform(get("/example/hello"))
      .andExpect(status().isOk())
      .andExpect(content().string("Hello World"));
  }

}
