package com.ynov.wsproject1.service;

import com.ynov.wsproject1.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TagService {
    Page<Tag> findAllTags(Pageable pageable);

    Optional<Tag> findById(Long id);
}
