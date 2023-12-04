package com.guiFerranti.SpringEventPro.domain.user.record;

import com.guiFerranti.SpringEventPro.domain.user.User;

public record UserDataList(
        String nome,
        String email,
        String cpf
) {
    public UserDataList(User user) {
        this(user.getNome(), user.getEmail(), user.getCpf());
    }
}
