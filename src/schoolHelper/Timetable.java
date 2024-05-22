package schoolHelper;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

/* 
 * 
 */
public class Timetable {
	@JsonProperty
	private String[][] subjects;

	private static File jsonFile = new File(
			String.join(File.separator, System.getProperty("user.home"), ".config", "schoolhelper", "Timetable.json"));
	
	public static Timetable readFromFile() {
		final ClassLoader loader = Timetable.class.getClassLoader();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		try {
			Timetable result = objectMapper.readValue(jsonFile, Timetable.class);
			if (result.subjects.length == 5) {
				return result;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Timetable();
	}

	public static void writeToFile(Timetable timetable) {
		if (timetable.subjects.length != 5) {
			throw new IllegalArgumentException();
		}
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			jsonFile.getParentFile().mkdirs();
			objectMapper.writeValue(jsonFile, timetable);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Timetable() {
		subjects = new String[5][8];
		for (String[] arr : subjects) {
			for (String s : arr) {
				s = "";
			}
		}
	}

	public String[][] getSubjects() {
		return subjects;
	}

	public String[] getSubjectsForDay(int weekday) {
		return subjects[weekday];
	}

	public void setSubjects(String[][] subjects) {
		if (subjects.length != 5) {
			throw new IllegalArgumentException();
		}
		this.subjects = subjects;
	}

	public void changeSubjectsForDay(int weekday, String[] subjects) {
		this.subjects[weekday] = subjects;
	}

	public void changeSpezificSubjectForDay(int weekday, int position, String subject) {
		this.subjects[weekday][position] = subject;
	}
}
