package com.kalgookso.jo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

class CalculateFeeTest {
    @Test
    @DisplayName("일반 요금제에 따른 통화 요금 계산")
    void calculateFee() {
        Phone phone = new Phone(new RegularPolicy(Money.wons(10), Duration.ofSeconds(10)));
        Money fee = phone.calculateFee();
        Assertions.assertEquals(Money.wons(0), fee);
    }

    @Test
    @DisplayName("심야 할인 요금제에 따른 통화 요금 계산")
    void calculateFee2() {
        Phone phone = new Phone(new NightlyDiscountPolicy(Money.wons(5), Money.wons(10), Duration.ofSeconds(10)));
        Money fee = phone.calculateFee();
        Assertions.assertEquals(Money.wons(0), fee);
    }

    @Disabled
    @Test
    @DisplayName("기본 정책과 부가 정책 합성하기")
    void calculateFee3() {
        NightlyDiscountPolicy nightlyDiscountPolicy = new NightlyDiscountPolicy(Money.wons(5), Money.wons(10), Duration.ofSeconds(10));
        TaxablePolicy taxablePolicy = new TaxablePolicy(nightlyDiscountPolicy, 0.05);
        RateDiscountablePolicy rateDiscountablePolicy = new RateDiscountablePolicy(taxablePolicy, Money.wons(1000));
        Phone phone = new Phone(rateDiscountablePolicy);
        Money fee = phone.calculateFee();
        Assertions.assertEquals(Money.wons(0), fee);
    }
}
