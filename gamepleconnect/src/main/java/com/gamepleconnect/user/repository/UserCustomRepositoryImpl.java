package com.gamepleconnect.user.repository;

import static com.gamepleconnect.user.domain.QUser.user;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class UserCustomRepositoryImpl implements UserCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public UserCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public boolean existsByEmail(String email) {
        Integer fetchOne = jpaQueryFactory
                .selectOne()
                .from(user)
                .where(user.email.eq(email))
                .fetchFirst();

        return fetchOne != null;
    }

    @Override
    public boolean existsByUserName(String username) {
        Integer fetchOne = jpaQueryFactory
                .selectOne()
                .from(user)
                .where(user.userName.eq(username))
                .fetchFirst();

        return fetchOne != null;
    }
}
