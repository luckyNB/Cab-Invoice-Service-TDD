package com.cab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class InvoiceService extends RideAdapter {
    private RideRepository rideRepository;
    RideCategory rideCategory;
    Map<String, List<Ride>> userRides = null;
    RideAdapter rideAdapter = new RideAdapter();

    public double calculateFare(RideCategory category, double distance, int time) {
        return rideAdapter.calculateFare(category, distance, time);
    }

    public InvoiceService() {
        this.rideRepository = new RideRepository();
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += super.calculateFare(ride.rideCategory, ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    public void addRide(String userId, Ride[] rides) {
        List<Ride> rideList = new ArrayList<>(Arrays.asList(rides));
        List<Ride> ridesFromRepoForDefinedUserId = this.userRides.get(userId);
        if (ridesFromRepoForDefinedUserId != null)
        {
            rideList.addAll(ridesFromRepoForDefinedUserId);
        }
        this.userRides.put(userId, rideList);
    }

    public InvoiceSummary getInvoiceService(String userId, RideCategory rideCategory) {
        this.rideCategory = rideCategory;
        return this.calculateFareForMultipleRide(rideRepository.getRides(userId));
    }


    public InvoiceSummary calculateFareForMultipleRide(List<Ride> listOfAllRides) {
        double totalFare = 0;
        for (Ride rides : listOfAllRides) {
            totalFare += this.calculateFare(rides.rideCategory, rides.distance, rides.time);
        }
        return new InvoiceSummary(listOfAllRides.size(), totalFare);
    }
    public InvoiceSummary getInvoiceSummary(String userId)
    {
        return this.calculateFareForMultipleRide(rideRepository.getRides(userId));
    }
}
