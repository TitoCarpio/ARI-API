package com.adminRiesgos.utils;

import com.adminRiesgos.models.entities.User;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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


    @JsonPropertyOrder({"type","coordinates"})
    public String convertToGeoJson(String data)
    {
        JSONObject result = new JSONObject();
        result.put("type","FeatureCollection");
        String trimmedString = data.trim();
        String cleanedString = trimmedString.substring(1,data.length()-1);
        // System.out.println(cleanedString);
        List<String> divided = List.of(cleanedString.split(","));

        List<JSONObject> points = divided.stream()
                .map(
                        this::convertToGeometry
                ).toList();
        result.put("geometry",points);

       System.out.println(result);
        //System.out.println(points);
        return result.toString();
    }


   public JSONObject convertToGeometry(String data){

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type","Point");
        jsonObject.put("coordinates",data);

        return jsonObject;
        }


}
