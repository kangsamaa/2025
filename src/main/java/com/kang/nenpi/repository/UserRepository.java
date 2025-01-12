package com.kang.nenpi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kang.nenpi.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
