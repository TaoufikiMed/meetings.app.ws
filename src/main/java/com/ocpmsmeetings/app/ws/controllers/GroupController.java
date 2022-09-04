package com.ocpmsmeetings.app.ws.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/groups")
public class GroupController {
	@GetMapping
	public String getGroup() {
		return "get group was called";
	}
	@PostMapping
	public String createGroup() {
		return "create group was called";
	}
	@PutMapping
	public String updateGroup() {
		return "update group was called";
	}
	@DeleteMapping
	public String deleteGroup() {
		return "delete group was called";
	}
}
