package org.modularframework.web.module.domain;

import java.io.Serializable;

import javax.persistence.*;

import lombok.*;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 25.
 */
@Entity
@Table(name = "MODULE_OPTION", uniqueConstraints = @UniqueConstraint(columnNames = {"MODULE_IDX", "OPTION_NAME"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModuleOptionEntity implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @Column(name = "MODULE_OPTION_SRL")
    @SequenceGenerator(
        name = "MODULE_OPTION_SRL_GEN",
        sequenceName = "MODULE_OPTION_SRL_SEQ",
        allocationSize = 1)
    @GeneratedValue(generator = "MODULE_OPTION_SRL_GEN", strategy = GenerationType.SEQUENCE)
    private Long moduleOptionIdx;

    @Setter(AccessLevel.NONE)
    @Column(name = "OPTION_NAME", nullable = false)
    private String name;

    @Column(name = "OPTION_VALUE")
    private String value;

    @Column(name = "OPTION_COMMENT")
    private String title;

    @Column(name = "ORDER_NUMBER")
    private int orderNumber;
}
