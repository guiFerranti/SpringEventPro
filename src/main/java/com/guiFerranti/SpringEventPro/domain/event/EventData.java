package com.guiFerranti.SpringEventPro.domain.event;

import com.guiFerranti.SpringEventPro.domain.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EventData(
        @NotBlank
        String titulo,
        @NotBlank
        String descricao,
        @NotBlank
        String data,
        @Valid
        @NotNull
        AddressData endereco,
        EventCategorias categorias,
        int max_participantes
) {
}
