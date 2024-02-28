package com.ynov.wsproject1.repository;

import com.ynov.wsproject1.model.Location;
import com.ynov.wsproject1.model.UserFull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;

@Repository
public interface LocationRepository extends JpaRepository<Location, String> {


}
