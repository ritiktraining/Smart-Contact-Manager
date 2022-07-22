package com.trantor.service;

import java.util.List;

import com.trantor.dto.ContactDto;

public interface ContactService {

	public List<ContactDto> fetchContactList();

	public ContactDto fetchbyfirstName(String firstName);

	ContactDto saveContact(ContactDto contactDto);

	public ContactDto deleteContact(Integer id);

}
