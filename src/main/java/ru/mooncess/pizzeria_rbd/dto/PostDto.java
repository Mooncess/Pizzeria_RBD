package ru.mooncess.pizzeria_rbd.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {
    public int idPost;
    public String nameOfPost;
    public float salary;
    public String nameOfDepartment;
}
