package com.adminRiesgos.models.entities;


import com.adminRiesgos.utils.Encoder;
import com.adminRiesgos.utils.JsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.json.JSONObject;

import java.util.List;


@Data
@NoArgsConstructor

public class User {

    //Properties
    private String document;
    private String name;
    private String last_name;
    private String card;
    private String type;
    private String cellphone;
    private String polygon; // TODO: Cambiar de String a JSON

    @JsonIgnore
    @ToString.Exclude
    private Encoder encoder = new Encoder();

    @JsonIgnore
    @ToString.Exclude
    private JsonConverter jsonConverter = new JsonConverter();

    //Constructor from array
    //Mostly use for text file
    public User(String[] data, String key){
        this.document = data[0];
        this.name = data[1];
        this.last_name = data[2];
        //this.card = this.Encrypt(data[3], key,data[1].concat(data[0]).concat(data[2]));
        this.card = this.Encrypt(data[3], key,this.name.concat(this.document.concat(this.last_name)));
        this.type = data[4];
        this.cellphone = data[5];
        this.polygon = this.jsonConverter.convertToGeoJson(data[6]);
    }




    // Methods


    public String Encrypt(String data, String key, String salt) {

        String result = "" ;
        try {
            result = encoder.encrypt(data,key,salt);}
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }


    public String Decrypt( String data, String key){
        String result = "";
        try {
            // TODO: fix final block not properly padded, due to salt being generated manually.
            result = encoder.decypt(data,key,this.name.concat(this.document.concat(this.last_name)));}
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

}
