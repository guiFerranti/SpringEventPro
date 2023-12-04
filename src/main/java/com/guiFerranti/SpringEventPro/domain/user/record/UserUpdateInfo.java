package com.guiFerranti.SpringEventPro.domain.user.record;

import com.guiFerranti.SpringEventPro.domain.address.AddressData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserUpdateInfo(
        @NotBlank
        Long id,
        String senha,
        String email,
        String telefone,
        AddressData endereco
) {
}
