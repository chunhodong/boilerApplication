package com.bronze.boiler.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CouponWalletRepositoryImpl implements CouponWalletRepositoryCst{
    private final JPAQueryFactory queryFactory;


}