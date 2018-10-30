package org.modularframework.context.view;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 30/10/2018
 */
@Service
public class TestModuleInitializationService implements ModuleInitializationService {
  @Override
  public List<Module> getModules() {
    return Collections.emptyList();
  }
}
