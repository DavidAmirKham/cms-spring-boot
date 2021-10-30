package com.idealump.cmsspringboot.repository;
import java.util.List;

import com.idealump.cmsspringboot.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NewsRepository extends JpaRepository<News, Integer> {
    List<News> findByNewsIgnoreCaseContaining(String sText);

    @Query(value = "SELECT * FROM news ORDER BY id DESC LIMIT 10", nativeQuery = true)
    List<News> findTop10ById();
    
    @Query(value = "SELECT coalesce(max(id), 0) FROM news", nativeQuery = true)
	public int maxId();
}
