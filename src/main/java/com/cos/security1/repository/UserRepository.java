package com.cos.security1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.security1.model.User;

// 기본 CRUD 함수를 JPArepo 가 들고 있음
// @RePository 어노테이션이 없어도 IOC가 되어짐
public interface UserRepository extends JpaRepository<User,Integer>{
	
	//findBy 규칙 -> Username 문법
	// select * from user where username = 1?;
	public User findByUsername(String username); // JPA query method

}
