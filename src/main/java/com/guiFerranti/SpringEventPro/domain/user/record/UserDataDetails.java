package com.guiFerranti.SpringEventPro.domain.user.record;

import com.guiFerranti.SpringEventPro.domain.address.AddressData;
import com.guiFerranti.SpringEventPro.domain.user.Roles;
import com.guiFerranti.SpringEventPro.domain.user.User;

public record UserDataDetails(
        String nome,
        String email,
        String cpf,
        String telefone,
        AddressData endereco,
        Roles role
){
    public UserDataDetails(User user) {
        this(user.getNome(), user.getEmail(), user.getCpf(), user.getTelefone(), user.getEndereco(), user.getRole());
    }
}
