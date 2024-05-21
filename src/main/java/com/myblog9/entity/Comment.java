package com.myblog9.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {                      //Comment is the child table for post

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)  //Many is Comments and one is the post, so @ManytoOne is used, Fwtchtype.LAZY means tht u dont load this comment when the post is loaded,load only post.
    @JoinColumn(name = "post_id", nullable = false)//@JoinColumn is used join two tables (based on the post_id which is foreign key)
    private Post post;  //post is one that's why i not used List.

    // Constructors, getters, and setters
}

