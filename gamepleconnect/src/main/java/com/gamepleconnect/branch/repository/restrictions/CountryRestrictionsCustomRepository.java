package com.gamepleconnect.branch.repository.restrictions;

import com.gamepleconnect.branch.domain.CountryRestrictions;
import com.gamepleconnect.branch.dto.request.CountryRestrictionsRequest;

import java.util.List;

public interface CountryRestrictionsCustomRepository {

    List<CountryRestrictions> findByCountryCodeAndLanguageCode(CountryRestrictionsRequest request);
}
