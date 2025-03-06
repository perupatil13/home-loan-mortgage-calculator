package com.jwt.spring.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.spring.security.entity.MyUser;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Long>{

	public Optional<MyUser> findByEmail(String email);
}
