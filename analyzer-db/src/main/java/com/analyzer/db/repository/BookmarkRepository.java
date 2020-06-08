package com.analyzer.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.analyzer.db.entity.BookmarkEntity;

public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Long> {

    List<BookmarkEntity> findByUserUUId(String uuId);
}
