package com.deloitte.marketfy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.deloitte.marketfy.entities.OrderRecord;

public interface OrderRecordRepository extends JpaRepository<OrderRecord, Integer> {
	
	@Query("FROM OrderRecord WHERE userId = :userId")
	List<OrderRecord> getUserOrders(@Param("userId") int userId);
	
	@Query("FROM OrderRecord WHERE (userId = :userId AND orderId = :orderId)")
	List<OrderRecord> getOrder(@Param("userId") int userId, @Param("orderId") String orderId);

}
