package com.adminRiesgos.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	String readTxtFile(MultipartFile file, String delim, String key);

	String readJsonFile(MultipartFile file, String delim, String key);

}
