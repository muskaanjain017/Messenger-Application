package com.example.Messenger.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Messenger.Entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    
}
