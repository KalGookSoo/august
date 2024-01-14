package com.kalgookso.jo;

import java.time.Duration;

public class RegularPolicy extends BasicRatePolicy {
    private final Money amount;
    private final Duration seconds;

    public RegularPolicy(Money amount, Duration seconds) {
        this.amount = amount;
        this.seconds = seconds;
    }

    @Override
    protected Money calculateCallFee(Call call) {
        return amount.times((double) call.getDuration().getSeconds() / seconds.getSeconds());
    }
}