package com.guiFerranti.SpringEventPro.domain.event;

import com.guiFerranti.SpringEventPro.domain.address.Address;

import java.time.LocalDateTime;

public record EventDetails(String titulo, String descricao, LocalDateTime data, Address endereco,
                           EventCategorias categorias, int max_participantes) {
    public EventDetails(Event event) {
        this(event.getTitulo(), event.getDescricao(), event.getData(), event.getEndereco(), event.getCategorias(),
                event.getMax_participantes());
    }
}
