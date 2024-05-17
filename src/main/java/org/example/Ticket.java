package org.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Ticket {
    @JsonProperty("origin")
    private String origin;
    @JsonProperty("origin_name")
    private String originName;
    @JsonProperty("destination")
    private String destination;
    @JsonProperty("destination_name")
    private String destinationName;
    @JsonProperty("departure_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yy")
    private LocalDate departureDate;
    @JsonProperty("departure_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "H:m")
    private LocalTime departureTime;
    @JsonProperty("arrival_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yy")
    private LocalDate arrivalDate;
    @JsonProperty("arrival_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "H:m")
    private LocalTime arrivalTime;
    @JsonProperty("carrier")
    private String carrier;
    @JsonProperty("stops")
    private int stops;
    @JsonProperty("price")
    private int price;

    public Duration getFlightDuration() {
        LocalDateTime startDate = LocalDateTime.of(departureDate, departureTime);
        LocalDateTime endDate = LocalDateTime.of(arrivalDate, arrivalTime);
        return Duration.between(startDate, endDate);
    }

    public String getOrigin() {
        return origin;
    }

    public String getOriginName() {
        return originName;
    }

    public String getDestination() {
        return destination;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public String getCarrier() {
        return carrier;
    }

    public int getStops() {
        return stops;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "origin='" + origin + '\'' +
                ", originName='" + originName + '\'' +
                ", destination='" + destination + '\'' +
                ", destinationName='" + destinationName + '\'' +
                ", departureDate=" + departureDate +
                ", departureTime=" + departureTime +
                ", arrivalDate=" + arrivalDate +
                ", arrivalTime=" + arrivalTime +
                ", carrier='" + carrier + '\'' +
                ", stops=" + stops +
                ", price=" + price +
                '}';
    }
}
