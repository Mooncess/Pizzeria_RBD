package ru.mooncess.pizzeria_rbd.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {
    private int idPost;
    private String nameOfPost;
    private float salary;
    private Department department;
}
