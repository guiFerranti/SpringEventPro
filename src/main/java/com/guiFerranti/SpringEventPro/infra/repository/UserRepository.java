package com.guiFerranti.SpringEventPro.infra.repository;

import com.guiFerranti.SpringEventPro.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByEmail(String email);
}
