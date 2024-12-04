package org.clubhive.repositories.jpa;

import org.clubhive.entities.DetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DetailRepositoryJpa extends JpaRepository<DetailEntity,Long> {
    @Query(value = "select p.code_promoter, sum(d.quantity) as total from details d,tickets t,buy_ticket bu,promoters p where d.id_ticket=t.id_ticket and bu.id_buy=d.id_buy and bu.state_buy = 'APPROVED' and t.id_event = ?1 and (p.id_promoter = bu.id_promoter or bu.id_promoter = null) GROUP BY p.code_promoter", nativeQuery = true)
    List<Object[]> getPromoterDashBoard(Long idEvent);

    @Query(value="select d.* from details d, buy_ticket b where d.id_buy = b.id_buy and b.qr_buy = ?1", nativeQuery = true)
    List<DetailEntity> findByQR(String qr);

    @Query(value="select t.name_ticket,sum(d.quantity) from buy_ticket b, tickets t, details d where d.id_buy = b.id_buy and t.id_ticket = d.id_ticket and b.id_promoter is not NULL and t.id_event = ?1 GROUP BY t.name_ticket", nativeQuery = true)
    List<Object[]> getTicketsSoldWithPromoterCodeByEventId(Long idEvent);

    @Query(value="select t.name_ticket,sum(d.quantity) from buy_ticket b, tickets t, details d where d.id_buy = b.id_buy and t.id_ticket = d.id_ticket and b.id_promoter is NULL and t.id_event = ?1 GROUP BY t.name_ticket", nativeQuery = true)
    List<Object[]> getTicketsSoldWithoutPromoterCodeByEventId(Long idEvent);
}
