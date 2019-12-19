package com.cab;

import java.util.HashMap;
import java.util.Map;

public class RideAdapter {
    Map<RideCategory, FareParams> paramsMap = null;
    private static final double MIN_DISTANCE_FARE = 10.0;
    private static final double MIN_TRAVEL_TIME = 5.0;
    private static final double COST_PER_TIME = 1.0;
    private static final double MIN_PREMIUM_RIDE_DISTANCE_FARE = 20.0;
    private static final double COST_PER_TIME_PREMIUM_RIDE = 2.0;
    private static final double MIN_TRAVEL_TIME_PREMIUM_RIDE = 10.0;

    public RideAdapter() {
        paramsMap = new HashMap<>();
        paramsMap.putAll(new FareInitializer().getFareParamList());
    }

    public double calculateFare(RideCategory category, double distance, int time) {

        if (category.equals(RideCategory.NORMALRIDE)) {
            Double fare = MIN_PREMIUM_RIDE_DISTANCE_FARE * distance + time * COST_PER_TIME_PREMIUM_RIDE;
            if (fare < 5)
                return MIN_TRAVEL_TIME_PREMIUM_RIDE;
            return fare;
        } else {
            Double fare = MIN_DISTANCE_FARE * distance + time * COST_PER_TIME;
            if (fare < 5)
                return MIN_TRAVEL_TIME;
            return fare;
        }


    }

}
