package com.trantor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trantor.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

	public Contact findByFirstName(String firstName);

}
