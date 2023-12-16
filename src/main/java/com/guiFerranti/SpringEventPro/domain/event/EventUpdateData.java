package com.guiFerranti.SpringEventPro.domain.event;

import com.guiFerranti.SpringEventPro.domain.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EventUpdateData(
        @NotNull
        Long id,
        String descricao,
        String data,
        Integer max_participantes,
        @Valid
        AddressData endereco) {}
