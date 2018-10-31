package org.syaku.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 31/10/2018
 */
@Entity
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExampleEntity {
  @Id
  private String name;
  @Column
  private String value;
}
