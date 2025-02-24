package net.engineeringdigest.journalApp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.repository.UserRepository;

@Service
public class JournalEntryService {

	@Autowired
	private JournalEntryRepository journalEntryRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	// save
	public ResponseEntity<Object> saveEntry(JournalEntry entity,String username) {
		User user = userRepository.findByusername(username);
		entity.setDate(LocalDateTime.now());

		JournalEntry saved= journalEntryRepository.save(entity);
		user.getJournalEntries().add(saved);
		userService.saveEntry(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Created successfully !!");
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// find
	public ResponseEntity<Object> findAll() {
		List<JournalEntry> list = journalEntryRepository.findAll();
		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("DataBase Is Empty");
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(list);

	}

	public ResponseEntity<Object> getJournalEntryById(ObjectId myid) {
		Optional<JournalEntry> finded = Optional.ofNullable(journalEntryRepository.findById(myid).orElse(null));

		if (finded.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nothing Exist With this ID in DataBase");
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(finded);
	}

	
	public ResponseEntity<Object> findByTitle(String myTitle) {
		Optional<JournalEntry> finded = journalEntryRepository.findByTitle(myTitle);

		if (finded.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nothing Exist With this Title in DataBase");
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(finded);
	}

	public ResponseEntity<Object> updateById(JournalEntry obj,ObjectId myid) {
		Optional<JournalEntry> finded = journalEntryRepository.findById(myid);

		if (finded.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
			        .body(Map.of("error", "Not Found", "message", "Nothing exists with this ID in the database"));

		}
		JournalEntry obje =finded.get();
		obje.setContent(obj.getContent());
		obje.setTitle(obj.getTitle());
		journalEntryRepository.save(obje);
		return ResponseEntity.status(HttpStatus.OK).body(obje);
	}

	public ResponseEntity<Object> deleteById(ObjectId myid,String username) {
		User user = userRepository.findByusername(username);

		Optional<JournalEntry> finded = journalEntryRepository.findById(myid);

		if (finded.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nothing Exist With this ID in DataBase");
		}
		user.getJournalEntries().removeIf(x -> x.getId().equals(myid));
		journalEntryRepository.deleteById(myid);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted from Journal and User");
	}
	
	public ResponseEntity<Object> deleteAllData() {
		journalEntryRepository.deleteAll();
		return ResponseEntity.status(HttpStatus.OK).body("Every Thing is Wanished");
	}

}
