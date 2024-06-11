package com.adminRiesgos.services;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	Map<String, Object> readTxtFile(MultipartFile file, String delim, String key);
	String readJsonFile(MultipartFile file, String delim, String key);

}
