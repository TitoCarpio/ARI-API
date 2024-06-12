package com.adminRiesgos.utils;


import com.adminRiesgos.models.entities.User;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class CustomUserDeserializer extends StdDeserializer<User> {
    public CustomUserDeserializer(){
        this(null);
    }

    public CustomUserDeserializer(Class<?> u){
        super(u);
    }

    @Override
    public User deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        User user = new User();
        ObjectCodec  codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        try
        {
            JsonNode card =  node.get("card");
           // user.setCard(user.Decrypt(card.asText()));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
