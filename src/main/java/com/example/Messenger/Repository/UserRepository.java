package com.example.Messenger.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Messenger.Entity.UserDetails;

@Repository
public interface UserRepository extends JpaRepository<UserDetails,Integer> {
    
}
