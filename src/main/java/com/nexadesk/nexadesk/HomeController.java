package com.nexadesk.nexadesk;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class HomeController {

    private final TicketRepository ticketRepository;

    public HomeController(TicketRepository ticketRepository) {

        this.ticketRepository = ticketRepository;

    }

    @GetMapping("/")

    public String home(Model model) {

        model.addAttribute("tickets", ticketRepository.findAll());

        model.addAttribute("ticketCount", ticketRepository.count());
        model.addAttribute("openCount",

                ticketRepository.findAll().stream()

                        .filter(ticket -> "Open".equals(ticket.getStatus()))

                        .count());
        model.addAttribute("inProgressCount",

                ticketRepository.findAll().stream()

                        .filter(ticket -> "In Progress".equals(ticket.getStatus()))

                        .count());
        return "index";

    }

}