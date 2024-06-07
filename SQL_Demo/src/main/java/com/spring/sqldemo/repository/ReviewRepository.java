package com.spring.sqldemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.sqldemo.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{

}
