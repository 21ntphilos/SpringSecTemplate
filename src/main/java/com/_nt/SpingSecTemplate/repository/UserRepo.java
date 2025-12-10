package com._nt.SpingSecTemplate.repository;

import com._nt.SpingSecTemplate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{


     User findByUsername(String Username);
}
