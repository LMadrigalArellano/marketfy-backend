package com.deloitte.marketfy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.deloitte.marketfy.entities.Selection;


public interface SelectionRepository extends JpaRepository<Selection, Integer> {
	
	@Query("FROM Selection WHERE ((userId = :userId) AND (storedIn = 'wishlist'))")
	List<Selection> getUserWishlist(@Param("userId") int userId);
	
	@Query("FROM Selection WHERE ((userId = :userId) AND (storedIn = 'cart'))")
	List<Selection> getUserCart(@Param("userId") int userId);

}
