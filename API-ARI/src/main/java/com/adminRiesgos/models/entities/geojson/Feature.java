package com.adminRiesgos.models.entities.geojson;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Feature {


    private String geometry;
    private List<String> properties;

    public Feature(String geometry){
        this.geometry = geometry;
    }
}
