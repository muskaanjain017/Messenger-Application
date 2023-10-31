package com.example.Messenger.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {

    private String comment_from;
    private String comment_to;
    private String message;
    
}
