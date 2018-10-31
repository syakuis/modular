package org.modularframework.web.module.domain;

import java.io.Serializable;

import javax.persistence.*;

import lombok.*;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 25.
 */
@Entity
@Table(name = "MODULE_OPTIONS", uniqueConstraints = @UniqueConstraint(columnNames = {"MODULE_IDX", "OPTIONS_NAME"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModuleOptionEntity implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @Column(name = "MODULE_OPTIONS_SRL")
    @SequenceGenerator(
        name = "MODULE_OPTIONS_SRL_GEN",
        sequenceName = "MODULE_OPTIONS_SRL_SEQ",
        allocationSize = 1)
    @GeneratedValue(generator = "MODULE_OPTIONS_SRL_GEN", strategy = GenerationType.SEQUENCE)
    private long moduleOptionIdx;

    @Setter(AccessLevel.NONE)
    @Column(name = "MODULE_IDX", nullable = false)
    private String id;

    @Setter(AccessLevel.NONE)
    @Column(name = "OPTIONS_NAME", nullable = false)
    private String name;

    @Column(name = "OPTIONS_VALUE")
    private String value;

    @Column(name = "OPTIONS_COMMENT")
    private String title;

    @Column(name = "ORDER_NUMBER")
    private int orderNumber;
}
