package com.deloitte.marketfy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloitte.marketfy.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
