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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modularframework.web.module.domain.ModuleEntity;
import org.modularframework.web.module.domain.ModuleOptionEntity;
import org.modularframework.web.module.repository.ModuleRepository;
import org.modularframework.web.module.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminModuleControllerTest {
  @Rule
  public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("build/generated-snippets");

  @Autowired
  private WebApplicationContext context;

  private MockMvc mockMvc;
  private ObjectMapper objectMapper;

  @Autowired
  private ModuleService moduleService;

  @Autowired
  private ModuleRepository moduleRepository;

  @Before
  public void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
      .defaultRequest(get("/admin/module").accept(MediaType.APPLICATION_JSON_UTF8))
      .defaultRequest(post("/admin/module").accept(MediaType.APPLICATION_JSON_UTF8))
      .alwaysDo(print())
      .alwaysExpect(status().isOk())
      .alwaysExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
      .alwaysDo(document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
      .apply(documentationConfiguration(this.restDocumentation))
      .build();

    this.objectMapper = new ObjectMapper();
  }

  @Test
  public void list() throws Exception {
    this.mockMvc.perform(get("/admin/module"));
  }

  @Test
  public void save() throws Exception {
    // Given
    String moduleId = UUID.randomUUID().toString();
    ModuleEntity moduleEntity = ModuleEntity.builder()
      .moduleId(moduleId).moduleName(moduleId)
      .moduleOptionEntities(Arrays.asList(
        ModuleOptionEntity.builder().name("1").build(),
        ModuleOptionEntity.builder().name("2").build()
      )).build();

    // Then
    this.mockMvc.perform(post("/admin/module")
      .content(this.objectMapper.writeValueAsString(moduleEntity))
      .contentType(MediaType.APPLICATION_JSON_UTF8));

    // Given
    ModuleEntity moduleEntity1 = moduleService.getModuleWithOption(moduleId);
    List<ModuleOptionEntity> moduleOptionEntities2 = Lists.newArrayList(moduleEntity1.getModuleOptionEntities());

    // When
    moduleOptionEntities2.get(0).setValue("1234");
    moduleOptionEntities2.remove(moduleOptionEntities2.get(1));
    moduleEntity.setModuleOptionEntities(moduleOptionEntities2);

    // Then
    this.mockMvc.perform(post("/admin/module/{moduleId}", moduleId)
      .content(this.objectMapper.writeValueAsString(moduleEntity1))
      .contentType(MediaType.APPLICATION_JSON_UTF8)).andDo(print());
  }
}
