package com.ensup.myresto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HistoricController {

	@GetMapping("/historic")
	public String showHistoric() {
		return "historic";
	}
}
