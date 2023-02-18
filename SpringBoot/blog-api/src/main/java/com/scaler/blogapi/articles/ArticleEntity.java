package com.scaler.blogapi.articles;

import com.scaler.blogapi.common.BaseEntity;
import com.scaler.blogapi.users.UserEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity(name = "articles")
public class ArticleEntity extends BaseEntity {

    @Column(unique = true, nullable = false, length = 150)
    String slug;

    @Column(nullable = false, length = 200)
    String title;

    @Column()
    String subtitle;

    @Column(nullable = false, length = 10000)
    String body;
    // String taglist;  //TODO : Implements String of Taglist

    @ManyToOne
    UserEntity author;

    @ManyToMany
    List<UserEntity> likedBy;
}
