package com.tibaestiago.apikafka.model;

import lombok.*;

import java.io.Serializable;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class FieldModel implements Serializable {

    private String nome;
    private String sobrenome;

    @Override
    public String toString(){
        return nome + " " + sobrenome + "\n";
    }

}
