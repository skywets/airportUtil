package org.example;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class FlightCalculator {
    public static Map<String, Ticket> minFlightTimeForCarriers(Tickets tickets) {
        Map<String, Ticket> minTickets = new HashMap<>();
        for (Ticket ticket : tickets.ticketList) {
            if (!minTickets.containsKey(ticket.getCarrier())) {
                minTickets.put(ticket.getCarrier(), ticket);
            } else {
                Ticket currentMinTicket = minTickets.get(ticket.getCarrier());
                if (currentMinTicket.getFlightDuration().compareTo(ticket.getFlightDuration()) > 0) {
                    minTickets.put(ticket.getCarrier(), ticket);
                }
            }
        }
        return minTickets;
    }

    public static double differenceBetweenAvgPriceAndMedian(Tickets tickets) {
        return avgPrice(tickets) - findMedian(tickets);
    }

    public static double findMedian(Tickets tickets) {
        tickets.ticketList.sort(Comparator.comparing(Ticket::getPrice));
        double median = tickets.ticketList.get(tickets.ticketList.size() / 2 - 1).getPrice();
        if (tickets.ticketList.size() % 2 == 0)
            median = (median + tickets.ticketList.get(tickets.ticketList.size() / 2).getPrice()) / 2.0;
        return median;
    }

    public static double avgPrice(Tickets tickets) {
        return tickets.ticketList.stream().mapToInt(Ticket::getPrice).average().orElse(0);
    }
}

