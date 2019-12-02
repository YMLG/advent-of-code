package advent.of.code.a2018.day4;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event implements Comparable<Event> {

	private LocalDateTime date;
	
	private String etat;

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	@Override
	public String toString() {
		return "Event [date=" + date + ", etat=" + etat + "]";
	}

	public Event(String data) {
		String[] datas = data.split("\\[|\\]");
		this.date =LocalDateTime.parse(datas[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		this.etat = datas[2];
	}

	@Override
	public int compareTo(Event o) {
		// TODO Auto-generated method stub
		return date.compareTo(o.getDate());
	}
	
	
}
