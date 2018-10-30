package org.modularframework.context.view;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 30/10/2018
 */
@Builder
@Data
@Setter(AccessLevel.NONE)
public final class ModuleOption implements Serializable {
  private final String name;
  private String value;
  private String title;
}
