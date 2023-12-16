package com.guiFerranti.SpringEventPro.domain.event;

import com.guiFerranti.SpringEventPro.domain.address.Address;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
}
