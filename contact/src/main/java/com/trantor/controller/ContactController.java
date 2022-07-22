package com.trantor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.trantor.dto.ContactDto;
import com.trantor.service.ExternalClientService;

@RestController
public class ContactController {

	@Autowired
	private ExternalClientService externalClientService;

	@GetMapping("/getAllContacts/{getDatafromDbOrExternalApi}")
	public ResponseEntity<String> fetchAll(@PathVariable("getDatafromDbOrExternalApi") Integer flag) {
		return externalClientService.fetchAll(flag);
	}

	@PostMapping("/saveContact/{postDatatoDbOrExternalApi}")
	public ResponseEntity<ContactDto> postData(@PathVariable("postDatatoDbOrExternalApi") Integer flag,
			@RequestBody ContactDto contactDto) {
		return externalClientService.postData(flag, contactDto);
	}

	@DeleteMapping("/deleteContact/{deleteDataFromDbOrExternalApi}/{id}")
	public ResponseEntity<ContactDto> deleteData(@PathVariable("deleteDataFromDbOrExternalApi") Integer flag,
			@PathVariable(value = "id", required = false) Integer id) {
		return externalClientService.deleteData(flag, id);
	}

	@GetMapping("/getContact/name/{getFirstNameFromDbOrExternalApi}/{firstName}")
	public ResponseEntity<ContactDto> fetchbyfirstName(@PathVariable("getFirstNameFromDbOrExternalApi") Integer flag,
			@PathVariable("firstName") String firstName) {
		return externalClientService.findByName(flag, firstName);
	}

}
