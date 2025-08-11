package com.gamepleconnect.branch.repository.restrictions;

import com.gamepleconnect.branch.domain.CountryRestrictions;
import static com.gamepleconnect.branch.domain.QCountryRestrictions.countryRestrictions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;
import java.util.Optional;

public class CountryRestrictionsCustomRepositoryImpl implements CountryRestrictionsCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    public CountryRestrictionsCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<CountryRestrictions> findByCountryCode(String countryCode) {
        return  jpaQueryFactory.selectFrom(countryRestrictions)
                .where(countryRestrictions.countryCode.eq(countryCode))
                .orderBy(countryRestrictions.updatedAt.desc())
                .fetch();

    }
}
