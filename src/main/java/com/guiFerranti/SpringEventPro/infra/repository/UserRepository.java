package com.guiFerranti.SpringEventPro.infra.repository;

import com.guiFerranti.SpringEventPro.domain.event.Event;
import com.guiFerranti.SpringEventPro.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findCompleteUserByEmail(@Param("email") String email);

    @Query("SELECT e FROM Event e JOIN e.subscribedUsers u WHERE u.id = :userId")
    List<Event> findEventsByUserId(@Param("userId") Long userId);

}
