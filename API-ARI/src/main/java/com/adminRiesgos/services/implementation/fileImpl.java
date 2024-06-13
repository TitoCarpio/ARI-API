package com.adminRiesgos.services.implementation;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.InputStreamReader;

import com.adminRiesgos.models.entities.User;
import com.adminRiesgos.utils.JsonConverter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.adminRiesgos.exception.FileEmptyException;
import com.adminRiesgos.exception.FileNameException;
import com.adminRiesgos.exception.JSONConversionError;
import com.adminRiesgos.services.FileService;

@Service
public class fileImpl implements FileService {

	private JsonConverter jsonConverter = new JsonConverter();
	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public String readTxtFile(MultipartFile file, String delim, String key) {

		// verifico que el archivo sea un txt
		if (!file.getOriginalFilename().endsWith(".txt")) {
			throw new FileNameException("El archivo debe tener extensión .txt");
		}

		// verifico que el archivo no este vacio
		if (file.isEmpty()) {
			throw new FileEmptyException("El archivo está vacío");
		}

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

			// Runs through every line of the file, creating an User and adding it to the
			// list
			List<User> users = reader.lines().map(u -> new User(u.split(delim), key)

			).toList();
			String jsonArray = jsonConverter.convertToJSON(users);
			//return the json
			return jsonArray;

		} catch (Exception e) {
			throw new JSONConversionError(e.getMessage());
		}
	}

	@Override
	public String readJsonFile(MultipartFile file, String delim, String key) {
		if (!file.getOriginalFilename().endsWith(".json")) {
			throw new FileNameException("El archivo debe tener extensión .json");
		}

		StringBuilder result = new StringBuilder();

		// verifico que el archivo no este vacio
		if (file.isEmpty()) {
			throw new FileEmptyException("El archivo está vacío");
		}

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			List<User> users = new ArrayList<>();
			String content = reader.lines().collect(Collectors.joining("\n"));
			JsonNode jsonNode = mapper.readTree(content); // This creates a Json Array
			for (JsonNode json : jsonNode) {
				User temp = new User();
				temp.setDocument(json.get("document").asText());
				temp.setName(json.get("name").asText());
				temp.setLast_name(json.get("last_name").asText());
				temp.setCard(temp.Decrypt(json.get("card").asText(), key));
				temp.setType(json.get("type").asText());
				temp.setCellphone(json.get("cellphone").asText());
				temp.setPolygon(String.valueOf(json.get("polygon")));
				users.add(temp);
				/* It works, just... just works */
			}

			for (User user : users) {
				result.append(user.getDocument()).append(",");
				result.append(user.getName() + ",");
				result.append(user.getLast_name() + ",");
				result.append(user.getCard() + ",");
				result.append(user.getType() + ",");
				result.append(user.getCellphone() + ",");
				result.append(user.getPolygon());
				result.append("\n");
			}
			
		} catch (Exception e) {
			throw new JSONConversionError(e.getMessage());
		}
		return result.toString();
		
	}

}
