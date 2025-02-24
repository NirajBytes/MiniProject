package net.engineeringdigest.journalApp.controller;

import org.springframework.web.bind.annotation.RestController;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.service.JournalEntryService;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
	// field injection
	private final JournalEntryService journalEntryService;
	@Autowired
	private UserRepository userRepository;

	public JournalEntryController(JournalEntryService journalEntryService) {
		this.journalEntryService = journalEntryService;
	}

	// saving data to database
	@PostMapping("{username}")
	public ResponseEntity<Object> saveEntry(@RequestBody JournalEntry entity, @PathVariable String username) {
		try {

			 journalEntryService.saveEntry(entity, username);
				return ResponseEntity.status(HttpStatus.CREATED).body(entity);

		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	// getting all data stored in database

//	@GetMapping
//	public ResponseEntity<Object> findAll() {
//		return journalEntryService.findAll();
//	}

	@GetMapping("/{username}")
	public ResponseEntity<Object> getAllJournalEntrisOfUser(@PathVariable String username) {
		User user = userRepository.findByusername(username);
		List<JournalEntry> finded = user.getJournalEntries();// getter is called
		if (finded != null && !finded.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FOUND).body(finded);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Jornal found for " + username);
	}

	// get data of specific id
	@GetMapping("/id/{myid}")
	public ResponseEntity<Object> getJournalEntryById(@PathVariable ObjectId myid) {

		return journalEntryService.getJournalEntryById(myid);
	}

	// find by title
	@GetMapping("/title/{myTitle}")
	public ResponseEntity<Object> findByTitle(@PathVariable String myTitle) {
		return journalEntryService.findByTitle(myTitle);
	}

	// update by id
	@PutMapping("/id/{myid}")
	public ResponseEntity<Object> updateById(@RequestBody JournalEntry obj, @PathVariable ObjectId myid) {
		return journalEntryService.updateById(obj, myid);
	}

	// delete by id
	@DeleteMapping("/id/{myid}/{username}")
	public ResponseEntity<Object> deleteById(@PathVariable ObjectId myid,@PathVariable String username) {
		return journalEntryService.deleteById(myid,username);
	}

	// delete all
	@DeleteMapping("/alldelete")
	public ResponseEntity<Object> deleteAllData() {
		return journalEntryService.deleteAllData();
	}

}
