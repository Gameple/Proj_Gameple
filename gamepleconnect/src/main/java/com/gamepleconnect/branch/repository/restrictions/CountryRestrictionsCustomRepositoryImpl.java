package com.gamepleconnect.branch.repository.restrictions;

import com.gamepleconnect.branch.domain.CountryRestrictions;
import static com.gamepleconnect.branch.domain.QCountryRestrictions.countryRestrictions;

import com.gamepleconnect.branch.dto.request.CountryRestrictionsRequest;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

public class CountryRestrictionsCustomRepositoryImpl implements CountryRestrictionsCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    public CountryRestrictionsCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<CountryRestrictions> findByCountryCodeAndLanguageCode(CountryRestrictionsRequest request) {
        return  jpaQueryFactory.selectFrom(countryRestrictions)
                .where(countryRestrictions.gameCode.eq(request.getGameCode()))
                .where(countryRestrictions.countryCode.eq(request.getCountryCode()))
                .where(countryRestrictions.languageCode.eq(request.getLanguageCode()))
                .orderBy(countryRestrictions.updatedAt.desc())
                .fetch();

    }
}
