package com.ocpmsmeetings.app.ws.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notes")
public class NoteController {
	@GetMapping
	public String getNote() {
		return "get note was called";
	}
	@PostMapping
	public String createNote() {
		return "create note was called";
	}
	@PutMapping
	public String updateNote() {
		return "update note was called";
	}
	@DeleteMapping
	public String deleteNote() {
		return "delete note was called";
	}
}
