package advent.of.code.a2020.day4;

import java.util.Arrays;
import java.util.function.Function;

public enum Fields {
	
	byr(s ->  s.length() == 4 && Integer.valueOf(s) > 1919 && Integer.valueOf(s) < 2003),
	iyr(s ->  s.length() == 4 && Integer.valueOf(s) > 2009 && Integer.valueOf(s) < 2021),
	eyr(s ->  s.length() == 4 && Integer.valueOf(s) > 2019 && Integer.valueOf(s) < 2031),
	hgt(s -> {
		if(!s.matches("^[0-9]{2,3}(cm|in)$") ) {
			return false;
		}
		if(s.endsWith("cm")) {
			int i = Integer.valueOf(s.replace("cm", ""));
			return i < 194 && i > 149;
		}
		int i = Integer.valueOf(s.replace("in", ""));
		return i < 77 && i > 58;
	}),
	hcl(s -> s.matches("#[0-9a-f]{6}")), 
	ecl(s -> Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(s)), 
	pid(s -> s.matches("[0-9]{9}"));
	
	public Function<String, Boolean> rule;
	
	private Fields(Function<String, Boolean> rule) {
		this.rule = rule;
	}

}
