package net.engineeringdigest.journalApp.controller;

import org.springframework.web.bind.annotation.RestController;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;

import java.util.Optional;

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
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<Object> findAll() {
		return userService.findAll();
	}

	@PostMapping
	public ResponseEntity<Object> saveEntry(@RequestBody User user) {

		return userService.saveEntry(user);
	}

	@PutMapping("/{username}")
	public ResponseEntity<Object> updateByUserName(@RequestBody User user,@PathVariable String username) {
		return userService.updateByUserName(user,username);
	}

	@GetMapping("/id/{myid}")
	public ResponseEntity<Object> getUserEntryById(@PathVariable ObjectId myid) {
		return userService.getUserEntryById(myid);
	}

	// delete by id
	@DeleteMapping("/id/{myid}")
	public ResponseEntity<Object> deleteById(@PathVariable ObjectId myid) {
		return userService.deleteById(myid);
	}

	// delete all
	@DeleteMapping("/alldelete")
	public ResponseEntity<Object> deleteAllData() {
		return userService.deleteAllData();
	}

}
