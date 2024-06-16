package com.adminRiesgos.utils;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CustomGeometrySerializer  extends StdSerializer<String> {

    public CustomGeometrySerializer()
    {
        this(null);
    }

    public CustomGeometrySerializer(Class<String> s) {
        super(s);
    }

    private List<String> extractCoordinates(String data) {
        List<String> coordinates = new ArrayList<>();

        // Define the regex pattern to match the coordinate pairs
        String regex = "\\((-?\\d+\\.\\d+) (-?\\d+\\.\\d+)\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);

        // Find all matches and add them to the list
        while (matcher.find()) {
            // Get the matched group (the whole pair)
            String coordinatePair = matcher.group(0);
            // Remove the parentheses
            coordinatePair = coordinatePair.substring(1, coordinatePair.length() - 1);
            // Add the coordinate pair to the list
            coordinates.add(coordinatePair);
        }

        return coordinates;
    }

    //Converting to GeoJson using custom serializer
    // Input is a String list , that contain the coordinates
    @Override
    public void serialize(String values, JsonGenerator gen, SerializerProvider provider) throws IOException {

        List<String> coords = this.extractCoordinates(values);


        gen.writeStartObject();
        gen.writeStringField("type","FeatureCollection");
        gen.writeArrayFieldStart("features");
        gen.writeStartObject();
        gen.writeStringField("type","Feature");
        gen.writeObjectFieldStart("geometry");
        gen.writeStringField("type","Polygon");
        gen.writeArrayFieldStart("coordinates");
        for(String c: coords){
            gen.writeString(c);
        }
        gen.writeEndArray();
        gen.writeEndObject();
        gen.writeNullField("properties");
        gen.writeEndObject();
        gen.writeEndArray();
        gen.writeEndObject();

    }
}
