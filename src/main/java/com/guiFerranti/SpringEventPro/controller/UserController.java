package com.guiFerranti.SpringEventPro.controller;

import com.guiFerranti.SpringEventPro.domain.user.*;
import com.guiFerranti.SpringEventPro.domain.user.record.UserDataDetails;
import com.guiFerranti.SpringEventPro.domain.user.record.UserDataList;
import com.guiFerranti.SpringEventPro.domain.user.record.UserDataRegistration;
import com.guiFerranti.SpringEventPro.domain.user.record.UserUpdateInfo;
import com.guiFerranti.SpringEventPro.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity registry(@RequestBody @Valid UserDataRegistration dados) {

        userService.saveUser(dados);

        return ResponseEntity.ok("Created");
    }

    @GetMapping
    public ResponseEntity<Page<UserDataList>> list(@PageableDefault(size=10) Pageable pagination) {

        return ResponseEntity.ok(userService.listUsers(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity list(@PathVariable Long id) {

        return ResponseEntity.ok(new UserDataDetails(repository.getReferenceById(id)));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody UserUpdateInfo dados) {

        return ResponseEntity.ok(userService.updateUser(dados));
    }

    
}
