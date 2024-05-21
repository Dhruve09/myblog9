package com.myblog9.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post {                                   //Post is the parent table of Comment

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "content" , nullable = false)
    private String content;



    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)//here one is post and many is comments, so @OnetoMany is used
                                                // CascadeType.ALL i.e all the changes done in the Post(parent table) will automaticaly be affected in the comment table(child)
                                               //for eg. if u delete the post then automaticaly the comment associated with post will also be deleted.
    private List<Comment> comments ;     //Because it is many we write list of comments because in list we can store multiple comment



}
