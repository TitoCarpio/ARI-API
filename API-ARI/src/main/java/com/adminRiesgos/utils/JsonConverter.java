package com.adminRiesgos.utils;

import com.adminRiesgos.models.entities.User;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@NoArgsConstructor
public class JsonConverter {

	private ObjectMapper mapper = new ObjectMapper();

	private SimpleModule module = new SimpleModule("CustomGeometrySerializer", new Version(1,0,0,null,null,null));



	public String convertToJSON(List<User> users) {

		String jsonUsers = "";

		try {
			jsonUsers = this.mapper.writeValueAsString(users);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return jsonUsers;
	}

	public String convertToUser(String data) {

		return null;
	}

	/*@JsonPropertyOrder({ "type", "coordinates"})
	public String convertToGeoJson(String data) {
		JSONObject result = new JSONObject();
		result.put("type", "FeatureCollection");

		String trimmedString = data.trim();
		String cleanedString = trimmedString.substring(1, data.length() - 1);
		// System.out.println(cleanedString);
		List<String> divided = List.of(cleanedString.split(","));

		List<JSONObject> points = divided.stream().map(this::convertToGeometry).toList();
		result.put("geometry", points);


		System.out.println(result);
		// System.out.println(points);
		return result.toString();
	}*/

	public String convertToGeoJson(String data) {
		this.module.addSerializer(String.class, new CustomGeometrySerializer());
		mapper.registerModule(module);

		String coords = "";

		try {
			coords = mapper.writeValueAsString(data);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}



		return coords;
	}

	public JSONObject convertToGeometry(String data) {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("type", "Point");
		jsonObject.put("coordinates", data);

		return jsonObject;
	}

}
