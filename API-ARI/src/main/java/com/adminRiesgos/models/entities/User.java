package com.adminRiesgos.models.entities;


import lombok.Data;
import lombok.NoArgsConstructor;



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
