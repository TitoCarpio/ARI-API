package com.adminRiesgos.services.implementation;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.adminRiesgos.exception.FileEmptyException;
import com.adminRiesgos.exception.FileNameException;
import com.adminRiesgos.services.FileService;

@Service
public class fileImpl implements FileService{

	@Override
	public Map<String, Object> readFile(MultipartFile file) {
		
		//verifico que el archivo sea un txt
		if (!file.getOriginalFilename().endsWith(".txt")) {
            throw new FileNameException("El archivo debe tener extensión .txt");
        }
		
		//verifico que el archivo no este vacio
		if (file.isEmpty()) {
            throw new FileEmptyException("El archivo está vacío");
        }
		
		System.out.println("si cumple");
		return null;
	}

}
