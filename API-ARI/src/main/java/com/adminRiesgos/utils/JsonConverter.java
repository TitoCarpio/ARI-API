package com.adminRiesgos.utils;

import com.adminRiesgos.models.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class JsonConverter {

    private ObjectMapper mapper = new ObjectMapper();

    public String convertToJSON(List<User> users){

        String jsonUsers =  "";

        try
        {
             jsonUsers = this.mapper.writeValueAsString(users);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return jsonUsers;}

    public String convertToString(){return null;}

    public String convertToArray(){return null;}


}
