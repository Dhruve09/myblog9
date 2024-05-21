package com.myblog9.payload;

import lombok.Data;

@Data
public class SignUpDto {
    //This class is a POJO class,pojo class means the class which have getters and setters and variables

    private String name;
    private String username;
    private String email;
    private String password;

}
