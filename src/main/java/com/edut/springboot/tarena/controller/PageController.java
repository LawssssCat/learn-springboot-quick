package com.edut.springboot.tarena.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping("/doIndexUI")
	public String doIndexUI() {
		return "/starter";
	}
}
