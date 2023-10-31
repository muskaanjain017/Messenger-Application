package com.example.Messenger.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.ArrayList;

import com.example.Messenger.DTO.RequestDTO;
import com.example.Messenger.Entity.UserDetails;
import com.example.Messenger.Entity.Comment;
import com.example.Messenger.Repository.UserRepository;
import com.example.Messenger.Repository.CommentRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    public String add_comment(RequestDTO requestDTO){
        
        //check if the names are valid
        boolean valid = validateUser(requestDTO.getComment_from(),requestDTO.getComment_to());
        if(valid==false) return "Invalid Request";

        //check if users is already preset in user_details table then only add it to comment table
        List<UserDetails> userList = userRepository.findAll();
        for(int i=0;i<userList.size();i++){
            if(userList.get(i).getComment_from().equals(requestDTO.getComment_from())
             && userList.get(i).getComment_to().equals(requestDTO.getComment_to())){
                Comment comment=new Comment();
                comment.setMessage(requestDTO.getMessage());
                comment.setPostedBy(userList.get(i).getUserId());
                
                UserDetails user = userRepository.findById(userList.get(i).getUserId()).get();
                List<Comment> ls = new ArrayList<>();
                ls.add(comment);
                user.setListOfComments(ls);

                userRepository.save(user);
                return "Comment added successfully";
            }
        }

        //if the users are not present add data to both tables
        UserDetails user = new UserDetails();
        user.setComment_from(requestDTO.getComment_from());
        user.setComment_to(requestDTO.getComment_to());

       Comment comment=new Comment();
       comment.setMessage(requestDTO.getMessage());
       
       userRepository.save(user);

       comment.setPostedBy(user.getUserId());
       
       List<Comment> ls = new ArrayList<>();
       ls.add(comment);
       user.setListOfComments(ls);
       
       userRepository.save(user);

       return "Comment added successfully";
    }
    
    public boolean validateUser(String comment_from, String comment_to){

        for(int i=0;i<comment_from.length();i++){
            char c= comment_from.charAt(i);
            if(((c>=65 && c<=90) || (c>=97 && c<=122))== false ){
                return false;
            }
             if((comment_from.isEmpty() || comment_from==" ") == true){
                return false;
             }
        }

        for(int i=0;i<comment_to.length();i++){
            char c= comment_to.charAt(i);
            if(((c>=65 && c<=90) || (c>=97 && c<=122))== false ){
                return false;
            }
             if((comment_to.isEmpty() || comment_to == " ") ){
                return false;
             }
        }
        return true;
    }

    public List<String> get_comment(String comment_to){
        List<String> comments = new ArrayList<>();
        List<UserDetails> userList = userRepository.findAll();
        for(int i=0;i<userList.size();i++){
            if(userList.get(i).getComment_to().equals(comment_to)){
                int userId = userList.get(i).getUserId();
                List<Comment> commentList = commentRepository.findAll();
                for(int j=0;j<commentList.size();j++){
                    if(commentList.get(j).getPostedBy()==userId){
                        comments.add(commentList.get(j).getMessage());
                    }
                }
            }
        }
        return comments;
    }

}
