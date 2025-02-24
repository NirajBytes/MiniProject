package net.engineeringdigest.journalApp.entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "journal_entries")
public class JournalEntry {
	@Id
	private ObjectId id;
	private String content;
	@NonNull
	private String title;
	private LocalDateTime date;
	@Override
	public String toString() {
		return "JournalEntry [id=" + id + ", content=" + content + ", title=" + title + "]";
	}
	
	
	
	
}
