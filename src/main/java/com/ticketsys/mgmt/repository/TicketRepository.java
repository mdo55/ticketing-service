package com.ticketsys.mgmt.repository;

import com.ticketsys.mgmt.domain.TicketInfo;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author mdoss
 */
public interface TicketRepository extends JpaRepository<TicketInfo, Integer> {

}
