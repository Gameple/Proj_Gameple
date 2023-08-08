package com.gamepleconnect.root.language.repository;

import com.gamepleconnect.root.language.domain.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Long> {

    Optional<Language> findByLangAlias(String lang);
}
