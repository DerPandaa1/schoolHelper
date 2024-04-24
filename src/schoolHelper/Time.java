package schoolHelper;

public class Time {
	private int hours;
	private int minutes;
	public Time(String s) {
		
	}
	public Time(int hours, int minutes) {
		if(hours >= 24 || minutes >= 60) {
			throw new IllegalArgumentException();
		}
		this.hours = hours;
		this.minutes = minutes;
	}
	public int getHours() {
		return hours;
	}
	public int getMinutes() {
		return minutes;
	}
	
	public void addTime(Time t) {
		this.hours += t.hours;
		this.minutes += minutes;
		if(minutes >= 60) {
			hours++;
			minutes -= 60;
		}
		if(hours >= 24 || minutes >= 60) {
			throw new IllegalArgumentException();
		}
	}
}
