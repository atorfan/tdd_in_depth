package com.codurance.shoppingbasket;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.concurrent.Callable;

class FutureClock extends Clock {

    private final Callable<Instant> future;
    private final ZoneId zone;

    FutureClock(Callable<Instant> future) {
        this.future = future;
        this.zone = ZoneId.systemDefault();
    }

    public FutureClock(Callable<Instant> future, ZoneId zone) {
        this.future = future;
        this.zone = zone;
    }

    public static Clock instance(Callable<Instant> future) {
        return new FutureClock(future);
    }

    @Override
    public ZoneId getZone() {
        return zone;
    }

    @Override
    public Clock withZone(ZoneId zone) {
        return new FutureClock(future, zone);
    }

    @Override
    public Instant instant() {
        try {
            return future.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
