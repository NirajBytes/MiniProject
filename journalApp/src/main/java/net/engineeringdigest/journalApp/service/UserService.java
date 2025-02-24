package net.engineeringdigest.journalApp.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	// save
	public ResponseEntity<Object> saveEntry(User user) {

		userRepository.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body("Created successfully !!");
	}

	// find
	public ResponseEntity<Object> findAll() {
		List<User> list = userRepository.findAll();
		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("DataBase Is Empty");
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(list);

	}

	// update

	public ResponseEntity<Object> updateByUserName(User user,String username) {
		User finded = userRepository.findByusername(username);

		if (finded == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nothing Exist With this username in DataBase");
		}
		finded.setUsername(user.getUsername());
		finded.setPassword(user.getPassword());
		userRepository.save(finded);
		return ResponseEntity.status(HttpStatus.OK).body("UserName And Password Updated in  in DataBase");

	}

	// find by id
	public ResponseEntity<Object> getUserEntryById(ObjectId myid) {
		Optional<User> finded = Optional.ofNullable(userRepository.findById(myid).orElse(null));

		if (finded.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nothing Exist With this ID in DataBase");
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(finded);
	}

	//delete by id
	public ResponseEntity<Object> deleteById(ObjectId myid) {
		Optional<User> finded = userRepository.findById(myid);

		if (finded.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nothing Exist With this ID in DataBase");
		}

		userRepository.deleteById(myid);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted");
	}

	
	public ResponseEntity<Object> deleteAllData() {
		userRepository.deleteAll();
		return ResponseEntity.status(HttpStatus.OK).body(Map.of("Message","Every Thing Is Wanished"));
	}

}
