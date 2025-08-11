package com.gamepleconnect.branch.repository.restrictions;

import com.gamepleconnect.branch.domain.CountryRestrictions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRestrictionsRepository extends JpaRepository<CountryRestrictions, Long>, CountryRestrictionsCustomRepository {

}
