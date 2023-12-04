package com.guiFerranti.SpringEventPro.service;

import com.guiFerranti.SpringEventPro.domain.user.User;
import com.guiFerranti.SpringEventPro.domain.user.UserRepository;
import com.guiFerranti.SpringEventPro.domain.user.record.UserDataDetails;
import com.guiFerranti.SpringEventPro.domain.user.record.UserDataList;
import com.guiFerranti.SpringEventPro.domain.user.record.UserDataRegistration;
import com.guiFerranti.SpringEventPro.domain.user.record.UserUpdateInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(UserDataRegistration dados) {
        User user = new User(dados);
        user.setSenha(dados.senha(), passwordEncoder);
        return repository.save(user);
    }

    public Page<UserDataList> listUsers(@PageableDefault(size=10) Pageable pagination) {
        var users = repository.findAll();
        return repository.findAll(pagination).map(UserDataList::new);
    }


    public UserDataDetails updateUser(UserUpdateInfo dados) {
        var user = repository.getReferenceById(dados.id());
        if (dados.email() != null) {
            user.setEmail(dados.email());
        }
        if (dados.telefone() != null) {
            user.setTelefone(dados.telefone());
        }
        if (dados.endereco() != null) {
            user.setEndereco(dados.endereco());
        }
        if (dados.senha() != null) {
            user.setSenha(dados.senha(), passwordEncoder);
        }

        return new UserDataDetails(user);
    }
}
