package com.stage.stage.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stage.stage.Exception.CustomException;
import com.stage.stage.entity.Notes;
import com.stage.stage.entity.Urls;
import com.stage.stage.reponses.CoreAcountResponse;
import com.stage.stage.reponses.DeleteResponse;
import com.stage.stage.service.NotesService;
import com.stage.stage.validators.Validators;

@Validated
@RestController
@RequestMapping("/api")
public class NoteController {
private NotesService noteservice;
@Autowired
public NoteController(NotesService noteservice) {
	super();
	this.noteservice = noteservice;
}
@GetMapping("/notes")
public CoreAcountResponse getnotes(){
	

	int total=0;
	 List<Notes> notes=noteservice.getNotes();
	 for(Notes temp :notes) {
		 total=total+1;
	 }
	return new CoreAcountResponse("sucess",notes,total);
	
}

@PostMapping("/createNote")
public CoreAcountResponse createNote(@Valid @RequestBody Notes note) {
	
	Notes note1 = noteservice.createUrls(note);
	
	return new CoreAcountResponse("success", note1,1);
}

@PutMapping("/updateNote/{id}")
public CoreAcountResponse editUrl(@Valid @RequestBody Notes note ,@Valid @PathVariable(name="id")String id){
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)  ;
	
	
	note.setId(id1);
	Notes note1= noteservice.updateNotes(note, id1);
  return new CoreAcountResponse("sucess", note1,1);
}

@DeleteMapping("/note/{id}")

public DeleteResponse deleteNoteById(@Valid @PathVariable(name="id") String id) {
	boolean val = Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid id type error");
	}
	Long id1=Long.parseLong(id)  ;
	
	noteservice.deleteUrl(id1);
	return new  DeleteResponse("success","Media deleted successfully");
}

@GetMapping("/note/{id}")
public CoreAcountResponse getUrlById(@Valid @PathVariable(name="id") String id){
	
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;
Notes note = noteservice.getNotesById(id1);
	return new CoreAcountResponse("sucess", note,1);
}



}
