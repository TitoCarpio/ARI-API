package com.adminRiesgos.models.entities;


import com.adminRiesgos.utils.Encoder;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private Encoder encoder = new Encoder();

    //Constructor from array
    public User(String[] data){
        this.document = data[0];
        this.name = data[1];
        this.last_name = data[2];
        this.card = this.Encrypt(data[3], data[7],data[1].concat(data[2]));
        this.type = data[4];
        this.cellphone = data[5];
        //this.polygon = data[6];
    }

    // Methods

    // TODO: Implement Cipher method
    public String Encrypt(String data, String key, String salt) {

        String result = "" ;
        try {
            result = encoder.encrypt(data,key,salt);}
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    // TODO: Implement Decipher method
    public String Decrypt( String data, String key, String salt){
        String result = "";
        try {
            result = encoder.decypt(data,key,salt);}
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

}
