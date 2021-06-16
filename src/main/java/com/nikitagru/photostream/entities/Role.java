package com.nikitagru.photostream.entities;

import lombok.Data;

import javax.persistence.*;

/***
 * Role entity
 */
@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
