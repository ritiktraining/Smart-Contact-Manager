package com.trantor.service;

import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.trantor.dto.ContactDto;
import com.trantor.entity.Contact;

@Service
public class ExternalClientService {

	Logger logger = LoggerFactory.getLogger(ExternalClientService.class);

	@Value("${uri}")
	private String uri;

	@Autowired
	private ContactServiceImpl contactServiceImpl;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ModelMapper modelMapper;

	String msg = "Please enter either 0 or 1";

	public ResponseEntity<String> fetchAll(Integer flag) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);

		try {
			if (flag == 1) {
				return restTemplate.exchange(uri + "searchDataApi/0", HttpMethod.GET, entity, String.class);
			}
			else if (flag == 0) {
				return new ResponseEntity(contactServiceImpl.fetchContactList(), HttpStatus.OK);
			}
		} catch (Exception e) {
			return restTemplate.exchange("http://10.50.2.206:9292/findAll", HttpMethod.GET, entity, String.class);
		}

		return new ResponseEntity<>(msg, HttpStatus.OK);

	}

	public ResponseEntity<ContactDto> postData(Integer flag, @RequestBody ContactDto contactDto) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		Contact contact = modelMapper.map(contactDto, Contact.class);

		HttpEntity<Contact> entity = new HttpEntity<>(contact, headers);

		if (flag == 1) {
			return restTemplate.exchange(uri + "saveDataApi/0", HttpMethod.POST, entity, ContactDto.class);

		} else if (flag == 0) {
			return new ResponseEntity<>(contactServiceImpl.saveContact(contactDto), HttpStatus.OK);
		}

		return restTemplate.exchange(msg, HttpMethod.POST, entity, ContactDto.class);
	}

	public ResponseEntity<ContactDto> deleteData(Integer flag, Integer id) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);

		if (flag == 1) {
			return restTemplate.exchange(uri + "delete/0/" + id, HttpMethod.DELETE, entity, ContactDto.class);
		} else if (flag == 0) {
			return new ResponseEntity<>(contactServiceImpl.deleteContact(id), HttpStatus.OK);
		}

		return restTemplate.exchange(msg, HttpMethod.DELETE, entity, ContactDto.class);

	}

	public ResponseEntity<ContactDto> findByName(Integer flag, String firstName) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);

		if (flag == 1) {
			return restTemplate.exchange(uri + "0/" + firstName, HttpMethod.GET, entity, ContactDto.class);
		} else if (flag == 0) {
			return new ResponseEntity<>(contactServiceImpl.fetchbyfirstName(firstName), HttpStatus.OK);
		}

		return restTemplate.exchange(msg, HttpMethod.GET, entity, ContactDto.class);

	}

}
