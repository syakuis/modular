package org.modularframework.web.module.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.modularframework.common.data.enums.YesOrNo;

import lombok.*;

/**
 * todo id 생성기에서 사용되는 시퀀스 hibernate 에서 자동으로 생성될수 있도록 변경
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 24.
 */
@Entity
@Table(name = "MODULE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModuleEntity implements Serializable {
  @Setter(AccessLevel.NONE)
  @Id
  @Column(name = "MODULE_IDX", nullable = false, length = 20)
  @SequenceGenerator(
    name = "MODULE_IDX_GEN",
    sequenceName = "MODULE_IDX_SEQ",
    allocationSize = 1
  )
  @GeneratedValue(
    generator = "MODULE_IDX_GEN",
    strategy = GenerationType.SEQUENCE)
  @GenericGenerator(
    name = "MODULE_IDX_GEN",
    strategy = "org.modularframework.data.jpa.StringSequenceIdentifier",
    parameters = {
      @org.hibernate.annotations.Parameter(
        name = "sequence_name", value = "MODULE_IDX_SEQ"),
      @org.hibernate.annotations.Parameter(
        name = "sequence_prefix", value = "MODU"),
    }
  )
  private String moduleIdx;

  @Setter(AccessLevel.NONE)
  @Column(name = "MODULE_ID", nullable = false, unique = true)
  private String moduleId;

  @Setter(AccessLevel.NONE)
  @Column(name = "MODULE_NAME", nullable = false)
  private String moduleName;

  @Column(name = "BROWSER_TITLE")
  private String browserTitle;

  @Column(name = "SKIN")
  private String skin;

  @Column(name = "LAYOUT_IDX")
  private String layoutIdx;

  @Enumerated(EnumType.STRING)
  @Column(name = "USE_THEME")
  private YesOrNo onlyUseTheme;

  @Setter(AccessLevel.NONE)
  @Column(name = "REG_DATE", nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDate;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
  @JoinColumn(name = "MODULE_IDX", nullable = false)
  private List<ModuleOptionEntity> moduleOptionEntities;

  @PrePersist
  public void prePersist() {
      this.creationDate = new Date();
  }
}
