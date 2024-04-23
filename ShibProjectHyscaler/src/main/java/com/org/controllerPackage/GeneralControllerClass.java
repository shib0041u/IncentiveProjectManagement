package com.org.controllerPackage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.org.DTO.Ceo;


@Controller
public class GeneralControllerClass {

	@RequestMapping(path="/index",method=RequestMethod.GET)
	public String index() {
		return "root/index";
	}
}
