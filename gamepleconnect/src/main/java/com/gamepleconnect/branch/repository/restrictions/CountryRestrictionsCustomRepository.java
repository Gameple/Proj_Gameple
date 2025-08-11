package com.gamepleconnect.branch.repository.restrictions;

import com.gamepleconnect.branch.domain.CountryRestrictions;

import java.util.List;

public interface CountryRestrictionsCustomRepository {

    List<CountryRestrictions> findByCountryCode(String countryCode);
}
