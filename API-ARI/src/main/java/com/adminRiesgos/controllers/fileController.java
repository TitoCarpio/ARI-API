package com.adminRiesgos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.adminRiesgos.services.FileService;



@RestController
@CrossOrigin("*")
@RequestMapping("/API/v1/ari/txt/")
public class fileController {
	
	@Autowired
	private FileService fileServices;
	
	@PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> readTxtFile(@RequestParam("file") MultipartFile data, @RequestParam("savePath") String savePath,
									  @RequestParam("delim") String delim, @RequestParam("key") String key){
		fileServices.readTxtFile(data,delim, key);


		
		return new ResponseEntity<>("termino", HttpStatus.OK);
	}

}
