package org.clubhive.repositories.implement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import org.clubhive.entities.DetailEntity;
import org.clubhive.model.Detail;
import org.clubhive.model.Promoter;
import org.clubhive.model.Ticket;
import org.clubhive.repositories.jpa.DetailRepositoryJpa;
import org.clubhive.utils.BuyMapper;
import org.clubhive.utils.DetailMapper;
import org.clubhive.utils.EventMapper;
import org.clubhive.utils.TicketMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.co.nobugs.nobugsexception.NoBugsException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class DetailRepository {

    private final DetailRepositoryJpa detailRepositoryJpa;
    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;
    private final PromoterRepository promoterRepository;

    @Transactional
    public Detail save(Detail detail) throws NoBugsException {

        if (detail.getIdBuy() == null)
            throw new NoBugsException("Buy must not be null", HttpStatus.BAD_REQUEST);

        DetailEntity detailEntity = new DetailEntity();

        detailEntity.setIdBuyEntity(BuyMapper.mapToBuyEntity(detail.getIdBuy()));
        detailEntity.setQuantity(detail.getQuantity());
        detailEntity.setIdTicket(TicketMapper.modelToEntity(detail.getIdTicket()));

        Ticket ticket = ticketRepository.findById(detail.getIdTicket().getId());

        detailEntity.getIdTicket().setEventId(EventMapper.mapEventToEventEntity(eventRepository.findById(Long.valueOf(ticket.getIdEvent()))));

        return DetailMapper
                .mapToDetail(detailRepositoryJpa.save(detailEntity));
    }

    public Detail findById(Long id) {
        return Stream.of(detailRepositoryJpa.findById(id).orElse(null))
                .filter(Objects::nonNull)
                .map(DetailMapper::mapToDetail)
                .findFirst()
                .orElse(null);
    }

    public List<Detail> findByQr(String qr) {

        return detailRepositoryJpa.findByQR(qr).stream().map(DetailMapper::mapToDetail).toList();
    }

    public Map<String, Integer> getPromotersDashboard(Long idEvent) throws NoBugsException {

        List<Object[]> results = detailRepositoryJpa.getPromoterDashBoard(idEvent);

        Map<String, Integer> promotersDashBoard = new HashMap<>();

        results.forEach(result -> promotersDashBoard.computeIfAbsent((String) result[0], k -> ((Number) result[1]).intValue()));

        List<Promoter> promotersEvent = promoterRepository.findByEvents(eventRepository.findById(idEvent));

        promotersEvent.forEach(promoter -> promotersDashBoard.computeIfAbsent(promoter.getCode(), k -> 0));

        return promotersDashBoard;
    }

    public Map<String, Integer> getTicketsSoldWithPromoterCodeByEventId(Long idEvent) {
        List<Object[]> results = detailRepositoryJpa.getTicketsSoldWithPromoterCodeByEventId(idEvent);
        Map<String, Integer> ticketsSold = new HashMap<>();
        results.forEach(result -> ticketsSold.put((String) result[0], ((Number) result[1]).intValue()));
        return ticketsSold;
    }

    public Map<String, Integer> getTicketsSoldWithoutPromoterCodeByEventId(Long idEvent) {
        List<Object[]> results = detailRepositoryJpa.getTicketsSoldWithoutPromoterCodeByEventId(idEvent);
        Map<String, Integer> ticketsSold = new HashMap<>();
        results.forEach(result -> ticketsSold.put((String) result[0], ((Number) result[1]).intValue()));
        return ticketsSold;
    }
}
