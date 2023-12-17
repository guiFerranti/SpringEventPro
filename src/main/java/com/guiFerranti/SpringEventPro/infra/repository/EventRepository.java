package com.guiFerranti.SpringEventPro.infra.repository;

import com.guiFerranti.SpringEventPro.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

}
