package com.nashtech.rootkies.repository;

import com.nashtech.rootkies.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
