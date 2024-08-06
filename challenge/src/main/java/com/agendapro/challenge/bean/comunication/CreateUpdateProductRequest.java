package com.agendapro.challenge.bean.comunication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUpdateProductRequest {
    private String name;
    private String description ;
    private String category;

    @Override
    public String toString() {
        return "CreateUpdateProductRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}