package schoolHelper;

public class Main {

	public static void main(String[] args) {
		Timetable t = new Timetable();
		String[][] s = new String[5][8];
		t.setSubjects(s);
		Timetable.writeToFile(t);
		Ui ui = new Ui();
		ui.showWindow();

		/* 
		int today = LocalDate.now().getDayOfWeek().getValue();
		ClassSubject subj = new ClassSubject();
		if(today == 3){
			subj.getCurrentClass(1);
		}
		if(today == 4){
			subj.getCurrentClass(2);
		}
		*/

	}

}
