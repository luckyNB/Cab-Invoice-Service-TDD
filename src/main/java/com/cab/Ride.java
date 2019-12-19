package com.cab;

public class Ride {
    public double distance;
    public int time;
    public RideCategory rideCategory;

    public Ride(RideCategory rideCategory, double distance, int time) {
        this.distance = distance;
        this.time = time;
        this.rideCategory = rideCategory;
    }
}
