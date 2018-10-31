package org.syaku.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 31/10/2018
 */
@Service
public class ExampleService {
  @Autowired
  private ExampleRepository exampleRepository;

  public List<ExampleEntity> getExamples() {
    return exampleRepository.findAll();
  }
}
