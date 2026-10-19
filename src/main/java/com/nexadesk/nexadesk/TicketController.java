package com.nexadesk.nexadesk;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.ModelAttribute;

@Controller

public class TicketController {

    private final TicketRepository ticketRepository;

    public TicketController(TicketRepository ticketRepository) {

        this.ticketRepository = ticketRepository;

    }

    @GetMapping("/tickets/new")

    public String showCreateForm(Model model) {

        model.addAttribute("ticket", new Ticket());

        return "create-ticket";

    }

    @PostMapping("/tickets")

    public String createTicket(@ModelAttribute Ticket ticket) {

        ticket.setStatus("Open");

        ticketRepository.save(ticket);

        return "redirect:/";

    }
    @PostMapping("/tickets/{id}/status")

    public String updateStatus(

            @org.springframework.web.bind.annotation.PathVariable Long id,

            @org.springframework.web.bind.annotation.RequestParam String status) {

        Ticket ticket = ticketRepository.findById(id)

                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        if (java.util.List.of("Open", "In Progress", "Closed").contains(status)) {

            ticket.setStatus(status);

            ticketRepository.save(ticket);

        }

        return "redirect:/";

    }
    @PostMapping("/tickets/{id}/delete")

    public String deleteTicket(

            @org.springframework.web.bind.annotation.PathVariable Long id) {

        ticketRepository.deleteById(id);

        return "redirect:/";

    }
}

