package com.tibaestiago.apikafka.model;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class AbstractModel{

    private String title;
    private String description;

    @Override
    public String toString() {
        return "AbstractModel{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
