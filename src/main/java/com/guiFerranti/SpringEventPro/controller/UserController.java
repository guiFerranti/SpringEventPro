package com.guiFerranti.SpringEventPro.controller;

import com.guiFerranti.SpringEventPro.domain.user.record.UserDataDetails;
import com.guiFerranti.SpringEventPro.domain.user.record.UserDataList;
import com.guiFerranti.SpringEventPro.domain.user.record.UserDataRegistration;
import com.guiFerranti.SpringEventPro.domain.user.record.UserUpdateInfo;
import com.guiFerranti.SpringEventPro.infra.repository.UserRepository;
import com.guiFerranti.SpringEventPro.service.AuthService;
import com.guiFerranti.SpringEventPro.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity registry(@RequestBody @Valid UserDataRegistration dados) {

        userService.saveUser(dados);

        return ResponseEntity.ok("Created");
    }

    @GetMapping
    public ResponseEntity<Page<UserDataList>> list(@PageableDefault(size=10) Pageable pagination) {

        return ResponseEntity.ok(userService.listUsers(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity list(@PathVariable Long id, HttpServletRequest request) {
        authService.ownerOrAdminOnly(id, request);
        return ResponseEntity.ok(new UserDataDetails(repository.getReferenceById(id)));
    }

    @PutMapping
    public ResponseEntity update(@RequestBody UserUpdateInfo dados, HttpServletRequest request) {
        authService.ownerOrAdminOnly(dados.id(), request);
        return ResponseEntity.ok(userService.updateUser(dados));
    }

    @DeleteMapping("/{id}")

    public ResponseEntity delete(@PathVariable Long id, HttpServletRequest request) {
        authService.ownerOrAdminOnly(id, request);
        userService.deleteUser(id);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/{id}/registrations")
    public ResponseEntity eventGetRegistrations(@PathVariable long id) {

        return ResponseEntity.ok(userService.eventGetRegistrations(id));
    }

    @DeleteMapping("/{userId}/registrations/{eventId}")
    public ResponseEntity eventRegistrationDelete(@PathVariable long userId, @PathVariable long eventId, HttpServletRequest request) {

        userService.eventRegistrationDelete(userId, eventId, request);
        return ResponseEntity.noContent().build();
    }
    
}
