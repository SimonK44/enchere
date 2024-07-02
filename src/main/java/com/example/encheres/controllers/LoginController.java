package com.example.encheres.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

		//Permet d'afficher la page de login
		@GetMapping("/login")
		public String login() {
			return "login";
		}	
}
