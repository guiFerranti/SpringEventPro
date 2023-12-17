package com.guiFerranti.SpringEventPro.service;

import com.guiFerranti.SpringEventPro.domain.event.*;
import com.guiFerranti.SpringEventPro.domain.user.User;
import com.guiFerranti.SpringEventPro.domain.user.record.UserDataList;
import com.guiFerranti.SpringEventPro.infra.repository.EventRepository;
import com.guiFerranti.SpringEventPro.infra.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Transactional
    public Event createEvent(EventData dados) {
        Event event = new Event(dados);
        return eventRepository.save(event);
    }

    public List<EventList> listEvents() {

        return eventRepository.findAll().stream().map(e -> new EventList(e)).collect(Collectors.toList());
    }


    public EventDetails eventDetails(long id) {
        return new EventDetails(eventRepository.getReferenceById(id));
    }

    @Transactional
    public EventDetails eventUpdate(EventUpdateData dados) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        var event = eventRepository.getReferenceById(dados.id());
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
        var event = eventRepository.getReferenceById(id);
        event.setExcluido(true);
    }

    @Transactional
    public void subscribeEvent(Long event_id, HttpServletRequest request) {
        User user = authService.getUserByToken(request);

        Event event = eventRepository.getReferenceById(event_id);

        event.addUser(user);

        eventRepository.save(event);
    }

    public List<UserDataList> getRegistrations(long id) {
        Event events = eventRepository.getReferenceById(id);

        return events.getSubscribedUsers().stream().map(UserDataList::new).toList();
    }
}
