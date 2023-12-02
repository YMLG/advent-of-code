package advent.of.code.a2023.day2;

public class Game {

	private int id;
	
	private String data;
	
	public Game(String ligne) {
		String[] split = ligne.split(": ",2);
		id = Integer.parseInt(split[0].replaceAll("\\D", ""));
		data = split[1];
	}
	
	public int getId() {
		return id;
	}
	
	public String getData() {
		return data;
	}
}
