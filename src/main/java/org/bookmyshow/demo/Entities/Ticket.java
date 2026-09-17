package org.bookmyshow.demo.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ticket")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketNo;

    private String seatNoBooked;
    private Double totalAmountPaid;

    @JoinColumn
    @ManyToOne
    private Show show_ticket;

    @JoinColumn
    @ManyToOne
    private User user;
}
