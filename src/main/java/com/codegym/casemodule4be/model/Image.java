package com.codegym.casemodule4be.model;

import javax.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Post post;

    private String image;

    public Image() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(Post post) {
        this.post = post;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public Post getStatus() {
        return post;
    }

    public String getImage() {
        return image;
    }
}
