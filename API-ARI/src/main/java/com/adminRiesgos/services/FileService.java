package com.adminRiesgos.services;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	Map<String, Object> readFile( MultipartFile file);

}
