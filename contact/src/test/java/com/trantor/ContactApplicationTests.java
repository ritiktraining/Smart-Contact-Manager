package com.trantor;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trantor.controller.ContactController;

@SpringBootTest
class ContactApplicationTests {
	
	@Autowired
	private ContactController contactController;

	

	@Test
    void contextLoads() throws Exception {
        assertThat(contactController).isNotNull();
    }
}
