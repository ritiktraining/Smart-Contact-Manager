package com.trantor.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trantor.service.ExcelService;

@RestController
public class ExcelController {

	@Autowired
	private ExcelService excelService;

	@GetMapping("/exportDbToExcel")
	public void exportToExcel(HttpServletResponse response) {
		excelService.listAll(response);
	}

	@PostMapping("/uploadDataFromExceltoDb")
	public void uploadDataFromExcel() {

		excelService.uploadAll();

	}

}
