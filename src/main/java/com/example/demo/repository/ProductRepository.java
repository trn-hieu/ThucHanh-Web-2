package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
	@Query(value= "SELECT CASE WHEN (COUNT(*) > 0) THEN 'true' ELSE 'false' END FROM product  WHERE code = :code", nativeQuery = true)
	boolean checktCodeExist(@Param("code")String code);
}
