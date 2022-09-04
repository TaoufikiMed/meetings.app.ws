package com.ocpmsmeetings.app.ws.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meetings")
public class MeetingController {
	@GetMapping
	public String getMeeting() {
		return "get meeting was called";
	}
	@PostMapping
	public String createMeeting() {
		return "create meeting was called";
	}
	@PutMapping
	public String updateMeeting() {
		return "update meeting was called";
	}
	@DeleteMapping
	public String deleteMeeting() {
		return "delete meeting was called";
	}
}
