package schoolHelper;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
public class Timetable {
	@JsonProperty
	private String[][] subjects;

	public static Timetable readFromFile() {
		final ClassLoader loader = Timetable.class.getClassLoader();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Timetable result = objectMapper.readValue(loader.getResource("Timetable.json"), Timetable.class);
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
			objectMapper.writeValue(new File(loader.getResource("Timetable.json").getAuthority()), timetable);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String[] getSubjectsForDay(int weekday) {
		return subjects[weekday];
	}

	public void changeSubjectsForDay(int weekday, String[] subjects) {
		this.subjects[weekday] = subjects;
	}
}
