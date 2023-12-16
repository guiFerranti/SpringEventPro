package com.guiFerranti.SpringEventPro.infra.repository;

import com.guiFerranti.SpringEventPro.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
