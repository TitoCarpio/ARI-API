package com.adminRiesgos.services.implementation;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.InputStreamReader;

import com.adminRiesgos.models.entities.User;
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
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			// String content = reader.lines().collect(Collectors.joining("\n"));

			List<User> users = reader
					.lines()
					.map(
							u ->
									new User(u.split(";"))

					)
			.toList();
            System.out.println(users);
            
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		System.out.println("si cumple");
		return null;
	}

}
