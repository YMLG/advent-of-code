package advent.of.code.a2023.day1;

public enum Number {
	one(1), two(2), three(3), four(4), five(5), six(6), seven(7), eight(8),  nine(9);
	
	private int value;
	
	private Number(int value) {
		this.value=value;
	}
	
	public int getValue() {
		return value;
	}
}
