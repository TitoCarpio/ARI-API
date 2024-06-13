package com.adminRiesgos.controllers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
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
@RequestMapping("/API/v1/ari/")
public class fileController {
	
	@Autowired
	private FileService fileServices;
	
	@PostMapping(value = "/txt/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> readTxtFile(@RequestParam("file") MultipartFile data, @RequestParam("savePath") String savePath,
									  @RequestParam("delim") String delim, @RequestParam("key") String key){
		
		String response = fileServices.readTxtFile(data,delim, key);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value =  "/json/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
	public	ResponseEntity<ByteArrayResource> readJsonFile(@RequestParam("file") MultipartFile data, @RequestParam("savePath") String savePath,
											 @RequestParam("delim") String delim, @RequestParam("key") String key) throws IOException
	{
		String result = fileServices.readJsonFile(data,delim,key);
		
		
//		File file = new File(filePath);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("Results", "result.txt");
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=result.txt");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .body(resource);
	}

}
