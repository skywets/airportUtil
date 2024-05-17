package org.example;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Укажите путь к файлу");
        String file = scanner.nextLine();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Tickets tickets = objectMapper
                .readValue(new File(file), Tickets.class);

        tickets.ticketList.removeIf(e -> !Objects.equals(e.getOriginName(), "Владивосток")
                || !Objects.equals(e.getDestinationName(), "Тель-Авив"));
        Map<String, Ticket> minimumFlightTimeForCarrier = FlightCalculator.minFlightTimeForCarriers(tickets);
        System.out.println("Минимальное время полета между городами \n" +
                "Владивосток и Тель-Авив для каждого \n" +
                "авиаперевозчика:");
        for (Ticket ticket : minimumFlightTimeForCarrier.values()) {
            System.out.printf("%s : %s \n", ticket.getCarrier(), timeToString(ticket.getFlightDuration()));
        }
        System.out.println("Средняя цена: " + FlightCalculator.avgPrice(tickets));
        System.out.println("Медиана: " + FlightCalculator.findMedian(tickets));
        System.out.println("Разница между средней ценой  и медианой: " + FlightCalculator.differenceBetweenAvgPriceAndMedian(tickets));

    }

    private static String timeToString(Duration duration) {
        long hour = duration.toHours();
        duration = duration.minusHours(hour);
        long min = duration.toMinutes();
        return String.format("%d часов %d минут", hour, min);
    }
}