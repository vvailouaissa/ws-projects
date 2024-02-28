package com.ynov.wsproject1.repository;

import com.ynov.wsproject1.model.UserPreview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPreviewRepository extends JpaRepository<UserPreview, Long> {
}
