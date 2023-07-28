package com.gamepleconnect.root.language.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "gameple_language")
@NoArgsConstructor
public class Language {

    @Id
    @Column(name = "language_code")
    private Long langCode;

    @Column(name = "language_alias", nullable = false)
    private String langAlias;
}
