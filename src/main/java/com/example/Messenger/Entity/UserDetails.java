package com.example.Messenger.Entity;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String comment_from;

    private String comment_to;


    @OneToMany(mappedBy = "userData", cascade = CascadeType.ALL)
    List<Comment> listOfComments = new ArrayList<>();
}
