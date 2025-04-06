package net.engineeringdigest.journalApp.entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Users")
public class User {
	@Id
	private ObjectId id;
	@Indexed(unique = true) // to be the unique username
	@NonNull // not to be null
	private String username;
	@NonNull
	private String password;
	//for reference 
	@DBRef
	private List<JournalEntry> journalEntries = new ArrayList<JournalEntry>();
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<JournalEntry> getJournalEntries() {
		return journalEntries;
	}
	public void setJournalEntries(List<JournalEntry> journalEntries) {
		this.journalEntries = journalEntries;
	}
}