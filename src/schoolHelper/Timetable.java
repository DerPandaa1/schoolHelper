package schoolHelper;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

/* 
 * 
 */
public class Timetable {
	@JsonProperty
	private String[][] subjects;

	public static Timetable readFromFile() {
		final ClassLoader loader = Timetable.class.getClassLoader();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Timetable result = objectMapper.readValue(new File("~/.config/schoolHelper/TimeTable.json"),
					Timetable.class);
			if (result.subjects.length == 5) {
				return result;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void writeToFile(Timetable timetable) {
		if (timetable.subjects.length != 5) {
			throw new IllegalArgumentException();
		}
		final ClassLoader loader = Timetable.class.getClassLoader();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			File jsonFile = new File("~/.config/schoolhelper/TimeTable.json");
			objectMapper.writeValue(jsonFile, timetable);
		} catch (IOException e) {
			e.printStackTrace();
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
