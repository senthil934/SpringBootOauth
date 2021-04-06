package com.pack.SpringBootJWT1.repository;



import org.springframework.data.repository.CrudRepository;

import com.pack.SpringBootJWT1.entity.User;

public interface UserRepository extends CrudRepository<User,Integer> {
    User findByUserName(String username);
}