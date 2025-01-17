package com.myblog9.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;


    private String name;


    private String email;


    private String body;
}
