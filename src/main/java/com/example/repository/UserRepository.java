package com.example.repository;

import com.example.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<Users, String> {
    Users findByLoginId(String loginId);
    Boolean existsByLoginId(String loginId);

}
