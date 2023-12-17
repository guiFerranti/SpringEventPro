package com.guiFerranti.SpringEventPro.domain.event;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.guiFerranti.SpringEventPro.domain.address.Address;
import com.guiFerranti.SpringEventPro.domain.user.User;
import com.guiFerranti.SpringEventPro.infra.exception.Exceptions;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Table(name = "events")
@Entity(name = "Event")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;

    private LocalDateTime data;

    @Embedded
    private Address endereco;

    @Enumerated(EnumType.STRING)
    private EventCategorias categorias;

    private Integer max_participantes;

    private Boolean excluido;

    @ManyToMany
    @JsonManagedReference
    @JoinTable(
            name = "user_event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> subscribedUsers;


    public Event(EventData dados) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        this.titulo = dados.titulo();
        this.descricao = dados.descricao();
        this.data = LocalDateTime.parse(dados.data(), formatter);
        this.endereco = new Address(dados.endereco());
        this.categorias = dados.categorias();
        this.max_participantes = dados.max_participantes();
        this.excluido = false;
    }

    public void addUser(User user) {
        if (subscribedUsers == null) {
            subscribedUsers = new ArrayList<>();
        }
        if (subscribedUsers.contains(user)) {
            throw new Exceptions.UserAlreadySubscribedException("User já está registrado nesse evento");
        }
        else if (subscribedUsers.size() >= max_participantes) {
            throw new Exceptions.EventFullException("Evento já está lotado");
        }
        else {
            subscribedUsers.add(user);
            user.getEventsToGo().add(this);
        }

    }
}
