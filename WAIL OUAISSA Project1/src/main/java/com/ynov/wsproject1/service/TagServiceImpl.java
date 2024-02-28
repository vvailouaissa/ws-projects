package com.ynov.wsproject1.service;

import com.ynov.wsproject1.model.Tag;
import com.ynov.wsproject1.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;



    @Cacheable("tags")
    @Override
    public Page<Tag> findAllTags(Pageable pageable) {

        // i used the @cacheable annotation and the @EnableCaching in the config class
        return tagRepository.findAll(pageable);

    }

    @Override
    public Optional<Tag> findById(Long id) {
        return tagRepository.findById(id);
    }
}
