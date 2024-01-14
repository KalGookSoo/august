package com.kalgookso.jo;

public interface RatePolicy {
    Money calculateFee(Phone phone);
}