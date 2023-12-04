package com.guiFerranti.SpringEventPro.domain.user.record;

import com.guiFerranti.SpringEventPro.domain.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserDataRegistration(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{9}")
        String cpf,
        @NotBlank
        String senha,
        @Pattern(regexp = "\\d{9,15}")
        @NotBlank
        String telefone,
        @Valid
        @NotNull
        AddressData endereco
) {

}
