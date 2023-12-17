package com.guiFerranti.SpringEventPro.service;

import com.guiFerranti.SpringEventPro.domain.event.EventDetails;
import com.guiFerranti.SpringEventPro.domain.user.User;
import com.guiFerranti.SpringEventPro.infra.exception.Exceptions;
import com.guiFerranti.SpringEventPro.infra.repository.EventRepository;
import com.guiFerranti.SpringEventPro.infra.repository.UserRepository;
import com.guiFerranti.SpringEventPro.domain.user.record.UserDataDetails;
import com.guiFerranti.SpringEventPro.domain.user.record.UserDataList;
import com.guiFerranti.SpringEventPro.domain.user.record.UserDataRegistration;
import com.guiFerranti.SpringEventPro.domain.user.record.UserUpdateInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthService authService;


    @Transactional
    public User saveUser(UserDataRegistration dados) {
        User user = new User(dados);
        user.setSenha(dados.senha(), passwordEncoder);
        return userRepository.save(user);
    }

    public Page<UserDataList> listUsers(@PageableDefault(size=10) Pageable pagination) {
        var users = userRepository.findAll();
        return userRepository.findAll(pagination).map(UserDataList::new);
    }

    @Transactional
    public UserDataDetails updateUser(UserUpdateInfo dados) {

        var user = userRepository.getReferenceById(dados.id());
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

    @Transactional
    public void deleteUser(Long id) {
            User user = userRepository.getReferenceById(id);
            user.setAtivo(false);
    }

    public List<EventDetails> eventGetRegistrations(long id) {

        return userRepository.findEventsByUserId(id).stream().map(EventDetails::new).toList();
    }

    public void eventRegistrationDelete(long userId, long eventId, HttpServletRequest request) {

        if (userId == authService.getUserByToken(request).getId()) {
            eventRepository.getReferenceById(eventId).getSubscribedUsers().remove(userRepository.getReferenceById(userId));
        }
        else {
            throw new Exceptions.NotAuthorizedUser("Only the owner user can delete events");
        }


    }
}
