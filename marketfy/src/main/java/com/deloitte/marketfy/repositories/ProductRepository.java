package com.deloitte.marketfy.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.deloitte.marketfy.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>, PagingAndSortingRepository<Product, Integer> {
		
	@Query("FROM Product WHERE title = :title")
	Optional<Product> findOneByTitle(@Param("title") String title);

	@Query("FROM Product WHERE title LIKE %:title%")
	List<Product> findByTitle(@Param("title") String title);
	
	@Query(value = "SELECT * FROM products WHERE price BETWEEN :minPrice AND :maxPrice", nativeQuery = true)
	List<Product> findByPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);

}
