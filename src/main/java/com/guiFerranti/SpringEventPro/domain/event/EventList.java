package com.guiFerranti.SpringEventPro.domain.event;

import com.guiFerranti.SpringEventPro.domain.address.Address;
import com.guiFerranti.SpringEventPro.domain.address.AddressData;

import java.time.LocalDateTime;

public record EventList(Long id,String titulo, String descricao, LocalDateTime data, Address local) {
    public EventList(Event event) {
        this(event.getId(), event.getTitulo(), event.getDescricao(), event.getData(), event.getEndereco());
    }
}
