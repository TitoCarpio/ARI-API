package com.adminRiesgos.models.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class User {

    //Properties
    private String document;
    private String name;
    private String last_name;
    private String card; // TODO: Implementar cifrado del numero de tarjeta
    private String type;
    private String cellphone;
    private String polygon; // TODO: Cambiar de String a JSON

    //Constructor from array
    public User(String[] data){
        this.document = data[0];
        this.name = data[1];
        this.last_name = data[2];
        this.card = data[3];
        this.type = data[4];
        this.cellphone = data[5];
        //this.polygon = data[6];
    }

    // Methods

    // TODO: Implement Cipher method
    public String Cipher() {
        return null;
    }

    // TODO: Implement Decipher method
    public String Decipher( String key){
        return null;
    }

    public String ToJson(){
        return null;
    }

}
