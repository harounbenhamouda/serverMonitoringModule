package com.stage.stage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.stage.Exception.CustomException;
import com.stage.stage.entity.Notes;
import com.stage.stage.entity.Urls;
import com.stage.stage.repository.NotesRepository;

@Service
public class NotesService {
	private NotesRepository noterepository;
@Autowired
	public NotesService(NotesRepository noterepository) {
		super();
		this.noterepository = noterepository;
	}

public  Notes createUrls(Notes note) {
	
	
	 if(  note.getElementid()<=0 ){
		throw new CustomException(" elemt id is not valid");
	}
	 if(  note.getElementid()==null){
			throw new CustomException(" elemt id is null");
		}
	 
	 
	 else return noterepository.save(note);
	
	
}
public Notes getNotesById(Long id){
	Notes note;
	if(id <=0 || id==null) {
		throw new CustomException("invalid id");
	}
	else {
		try{

			note=noterepository.findById(id).get();
			return note;
		}catch(Exception e){
		
		if(e.getMessage() == "No value present") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		 throw new CustomException("ma famech"); 
	}
}	
}
public List<Notes> getNotes() {
	// TODO Auto-generated method stub
	return noterepository.findAll();
}
public void deleteUrl(Long id) {
	if(id <=0 || id==null) {
		throw new CustomException("invalid id");
	}
	else {
		try{

			 noterepository.deleteById(id);
			
		}catch(Exception e){
		
		if(e.getMessage() == "No class") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		 throw new CustomException("resource doesn t not exist"); 
	}
	
}
	
}
public Notes updateNotes( Notes note,Long id) {
	Notes note1;
	
	if(id <=0 ) {
		throw new CustomException("invalid id");
	}
	
	
	
	else {
		try{

			note1 =noterepository.findById(id).get();
						
			
			

			
			
			 if  (note.getElementid()==null) {
				note.setElementid(note1.getElementid());
			}
			else if  (note.getContent()==null) {
				note.setContent(note1.getContent());
			}
			else if(note.getVisibility()==0) {
				note.setVisibility(note1.getVisibility());
			}
			return noterepository.saveAndFlush(note);
		}catch(Exception e){
		
		if(e.getMessage() == "No value present") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		
		 throw new CustomException("verify your fields"); 
	}
	
	}
	
	}







}
