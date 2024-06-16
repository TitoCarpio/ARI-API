package com.adminRiesgos.models.entities;

import com.adminRiesgos.exception.FileEmptyException;
import com.adminRiesgos.utils.Encoder;
import com.adminRiesgos.utils.JsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor

public class User {

	// Properties
	@NotNull(message = "Document cannot be empty")
	@NotEmpty(message = "Document cannot be empty")
	@Pattern(regexp = "[0-9]+-\\d", message = "Document provided does not follow allowed format, must be ########-# ")
	private String document;

	@NotEmpty(message = "name cannot be empty")
	private String name;

	@NotEmpty(message = "Last name cannot be empty")
	private String last_name;

	@NotEmpty(message = "card cannot be empty")
	@NotNull(message = "card can't be null")
	private String card;

	@NotEmpty(message = "card must have a type")
	private String type;


	private String cellphone;

	@NotEmpty(message = "polygon cannot be empty")
	private String polygon; // TODO: Cambiar de String a JSON

	@JsonIgnore
	@ToString.Exclude
	private Encoder encoder = new Encoder();

	@JsonIgnore
	@ToString.Exclude
	private JsonConverter jsonConverter = new JsonConverter();

	// Constructor from array
	// Mostly use for text file
	public User(String[] data, String key) {
		if(data[0].isEmpty())
			throw new FileEmptyException("Document cannot be empty");
		else
			this.document = data[0];

		if(data[1].isEmpty())
			throw new FileEmptyException("Document cannot be empty");
		else
			this.name = data[1];

		if(data[2].isEmpty())
			throw new FileEmptyException("Document cannot be empty");
		else
			this.last_name = data[2];

		if(data[3].isEmpty())
			throw new FileEmptyException("Document cannot be empty");
		else
			this.card = this.Encrypt(data[3], key, this.name.concat(this.document.concat(this.last_name)));

		if(data[4].isEmpty())
			throw new FileEmptyException("Document cannot be empty");
		else
			this.type = data[4];

		if(data[5].isEmpty())
			throw new FileEmptyException("Document cannot be empty");
		else
			this.cellphone = data[5];

		if(data[6].isEmpty())
			throw new FileEmptyException("Document cannot be empty");
		else
			this.polygon = this.jsonConverter.convertToGeoJson(data[6]);



	}

	// Methods

	public String Encrypt(String data, String key, String salt) {

		String result = "";
		try {
			result = encoder.encrypt(data, key, salt);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public String Decrypt(String data, String key) {
		String result = "";
		try {
			result = encoder.decypt(data, key, this.name.concat(this.document.concat(this.last_name)));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return result;
	}

}
