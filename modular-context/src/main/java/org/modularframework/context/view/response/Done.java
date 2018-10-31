package org.modularframework.context.view.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 31/10/2018
 */
@Builder
@Data
@Setter(AccessLevel.NONE)
public class Done<T> {
  private final String message;
  private final boolean error;
  /**
   * 어떤 오류인지 판단할 수 있는 값을 직접 만들어 사용한다.
   */
  private final String errorCode;
  private final T data;
}
