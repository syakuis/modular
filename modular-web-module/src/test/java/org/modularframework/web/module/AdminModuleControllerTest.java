package org.modularframework.web.module;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 31/10/2018
 */

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modularframework.web.module.domain.ModuleEntity;
import org.modularframework.web.module.domain.ModuleOptionEntity;
import org.modularframework.web.module.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminModuleControllerTest {
  @Rule
  public JUnitRestDocumentation restDocumentation =
    new JUnitRestDocumentation("build/generated-snippets");

  @Autowired
  private WebApplicationContext context;

  private MockMvc mockMvc;
  private ObjectMapper objectMapper;

  @Autowired
  private ModuleService moduleService;

  private static final String PATH = "/admin/module";
  private static final String MODULE_ID = "test";
  private String moduleIdx;

  @Before
  public void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
      .defaultRequest(get(PATH).accept(MediaType.APPLICATION_JSON_UTF8))
      .defaultRequest(post(PATH).accept(MediaType.APPLICATION_JSON_UTF8))
      .alwaysDo(document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
      .apply(documentationConfiguration(this.restDocumentation))
      .build();
    this.objectMapper = new ObjectMapper();

    this.moduleIdx = initData(MODULE_ID).getModuleIdx();
  }

  @After
  public void exit() {
    if (this.moduleIdx != null) {
      this.moduleService.delete(this.moduleIdx);
    }
  }

  private ModuleEntity initData(String moduleId) {
    ModuleEntity moduleEntity = ModuleEntity.builder()
      .moduleId(moduleId).moduleName(moduleId)
      .moduleOptionEntities(Arrays.asList(
        ModuleOptionEntity.builder().name("kr").value("한국").build(),
        ModuleOptionEntity.builder().name("us").build()
      )).build();

    return moduleService.save(moduleEntity);
  }

  @Test
  public void pageList() throws Exception {
    this.mockMvc.perform(get(PATH))
      .andExpect(status().isOk());
  }

  @Test
  public void view() throws Exception {
    this.mockMvc.perform(get(PATH + "/{moduleIdx}", this.moduleIdx)).andExpect(status().isOk());
  }

  @Test
  public void save() throws Exception {
    String moduleId = UUID.randomUUID().toString();
    ModuleEntity moduleEntity = ModuleEntity.builder()
      .moduleId(moduleId).moduleName(moduleId)
      .moduleOptionEntities(Arrays.asList(
        ModuleOptionEntity.builder().name("1").build(),
        ModuleOptionEntity.builder().name("2").build()
      )).build();

    this.mockMvc.perform(post(PATH)
      .content(this.objectMapper.writeValueAsString(moduleEntity))
      .contentType(MediaType.APPLICATION_JSON_UTF8)).andDo(print()).andDo(new ResultHandler() {
      @Override
      public void handle(MvcResult result) throws Exception {
        String content = result.getResponse().getContentAsString();
        Map<String, Object> response = objectMapper.readValue(content, new TypeReference<Map<String, Object>>() {});
        Object data = response.get("data");
        if (data instanceof Map) {
          Map<String, String> module = (Map<String, String>) data;
          moduleService.delete(module.get("moduleIdx"));
        }

      }
    }).andExpect(status().isOk());
  }

  @Test
  public void delete() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.delete(PATH + "/{moduleIdx}", this.moduleIdx)
      .contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk());

    this.moduleIdx = null;
  }
}
