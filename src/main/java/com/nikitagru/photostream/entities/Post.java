package com.nikitagru.photostream.entities;

import lombok.Data;

import javax.persistence.*;

/***
 * Post entity
 */
@Entity
@Data
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imagename;

    private String cutImageName;    // name before first point (needs for modal windows generating)

    private String text;

}
