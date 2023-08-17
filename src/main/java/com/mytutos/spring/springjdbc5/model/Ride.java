package com.mytutos.spring.springjdbc5.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Ride {

    private Integer id;
    private String name;
    private int duration;
}
