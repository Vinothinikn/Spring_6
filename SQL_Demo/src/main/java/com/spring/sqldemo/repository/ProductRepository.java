package com.spring.sqldemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.sqldemo.entity.Product;

import jakarta.transaction.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	@Modifying
	@Transactional
	@Query(value = "update product set price = :price where name = :productName", nativeQuery = true)
	 int updatePrice(String productName, double price);
}
