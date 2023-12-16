package com.guiFerranti.SpringEventPro.service;

import com.guiFerranti.SpringEventPro.domain.address.Address;
import com.guiFerranti.SpringEventPro.domain.address.AddressData;
import com.guiFerranti.SpringEventPro.domain.event.*;
import com.guiFerranti.SpringEventPro.infra.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    @Transactional
    public Event createEvent(EventData dados) {
        Event event = new Event(dados);
        return repository.save(event);
    }

    public List<EventList> listEvents() {

        return repository.findAll().stream().map(e -> new EventList(e)).collect(Collectors.toList());
    }


    public EventDetails eventDetails(long id) {
        return new EventDetails(repository.getReferenceById(id));
    }

    @Transactional
    public EventDetails eventUpdate(EventUpdateData dados) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        var event = repository.getReferenceById(dados.id());
        if (dados.descricao() != null) {
            event.setDescricao(dados.descricao());
        }
        if (dados.data() != null) {
            event.setData(LocalDateTime.parse(dados.data(), formatter));
        }
        if (dados.endereco() != null) {
            event.setEndereco(event.getEndereco().updateAddress(dados.endereco()));
        }
        if (dados.max_participantes() != null) {
            event.setMax_participantes(dados.max_participantes());
        }
        return new EventDetails(event);
    }

    @Transactional
    public void eventDelete(Long id) {
        var event = repository.getReferenceById(id);
        event.setExcluido(true);
    }
}
