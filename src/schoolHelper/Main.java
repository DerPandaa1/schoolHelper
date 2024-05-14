package schoolHelper;
import java.time.LocalDate;

public class Main {

	public static void main(String[] args) {
		int today = LocalDate.now().getDayOfWeek().getValue();
		ClassSubject subj = new ClassSubject();
		if(today == 3){
			subj.getCurrentClass(1);
		}
		if(today == 4){
			subj.getCurrentClass(2);
		}

	}

}
