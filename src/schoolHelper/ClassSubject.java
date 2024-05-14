package schoolHelper;

import java.time.LocalTime;

public class ClassSubject {
    String todaysSubjects[];
    String wednesday[] = {"Religion","Englisch", "Deutsch","Politik"};
    String thursday[]= {"GIT","Wirtschaft","EVP","STD"};
    int time;
    public void getCurrentClass(int schoolday) {
        setup(schoolday);
        getTime();
        System.out.print(getSubjectNow());
    }

    private void getTime() {
        LocalTime timeNoFormat = LocalTime.now();
        time = timeNoFormat.getHour() * 100 + timeNoFormat.getMinute();
    }

    private String getSubjectNow() {
        if(time > 730 && time < 910){
            return todaysSubjects[0];
        }
        if(time > 920 && time < 1100){
            return todaysSubjects[1];
        }
        if(time > 1110 && time < 1250){
            return todaysSubjects[2];
        }
        if(time > 1300 && time < 1440){
            return todaysSubjects[3];
        }
        return null;
    }

    private void setup(int schoolday) {
        if (schoolday == 1) {
            todaysSubjects = wednesday;
        }
        else{
            todaysSubjects = thursday;
        }
        
    }

}