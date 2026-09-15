package org.bookmyshow.demo.Repository;

import org.bookmyshow.demo.Entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}
