package advent.of.code.a2021.day8;

import java.util.Arrays;

public class Rules {
	
	private String[] numbers = new String[10];
		
	
	public Rules(String[] input) {
		Arrays.asList(input).forEach(i ->{
			switch (i.length()) {
			case 2:
				numbers[1] = i;
				break;
			case 3:
				numbers[7] = i;
				break;
			case 4:
				numbers[4] = i;
				break;
			case 7:
				numbers[8] = i;
				break;
			default:
				break;
			}
		});
		Arrays.asList(input).forEach(i ->{
			if(Arrays.asList(numbers[1].split("")).stream().allMatch(s -> i.contains(s))) {
				switch (i.length()) {
				case 6:
					if(Arrays.asList(numbers[4].split("")).stream().allMatch(s -> i.contains(s))) {
						numbers[9] = i;
					
					}else {
						numbers[0] = i;
					}
					break;
				case 5:
					numbers[3] = i;
					break;
				default:
					break;
				}
				
			}else if(i.length() == 6) {
				numbers[6] = i;
			}
		});
		Arrays.asList(input).forEach(i ->{
			if(i.length() == 5 && !i.equals(numbers[3])) {
				if(Arrays.asList(i.split("")).stream().allMatch(s -> numbers[9].contains(s))) {
					numbers[5] = i;
				}else {
					numbers[2] = i;
				}
			}
		});
	}
	
	public int getInt(String s) {
		int i = 0;
		while(numbers[i].length() != s.length() || !Arrays.asList(numbers[i].split("")).stream().allMatch(j -> s.contains(j))) {
			i++;
		}
		return i;
	}

}
