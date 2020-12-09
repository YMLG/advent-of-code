package advent.of.code.a2020.day9;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Queue;

import advent.of.code.utils.LectureFichiersUtils;

public class MainDay9 {
	
	private static final int preambleSize = 25;
	
	private static List<Long> preamble = new ArrayList<>();
	
	public static void main(String[] args) {
		System.out.println("Question 2 - "+ getReponseQuestion2());
	}

	private static long getReponseQuestion1() {
		Iterator<Long> datas = getData();
		Long number = null;
		while(preamble.size() < preambleSize | chekIsSumOfElements(number = datas.next())) {
			addToPreamble(number);
		}
		return number;
	}
	
	private static boolean chekIsSumOfElements(long number) {
		for(long i : preamble) {
			for(long j : preamble) {
				if(i != j && i + j == number) {
					return true;
				}
			}
		}
		return false;
	}

	private static void addToPreamble(long number) {
		if(preamble.size() == preambleSize) {
			preamble.remove(0);
		}
		preamble.add(number);
	}
	
	
	private static long getReponseQuestion2() {
		long q1 = getReponseQuestion1();
		System.out.println("Question 1 - "+ q1);
		
		Iterator<Long> datas = getData();
		Queue<Long> numbers = new LinkedList<>();
		Long sum = 0l;
		while(sum != q1) {
			Long number = datas.next();
			numbers.add(number);
			sum += number;
			while(sum > q1) {
				sum -= numbers.poll();	
			}
		}
		LongSummaryStatistics stats = numbers.stream().mapToLong(l -> l ).summaryStatistics();		
		return stats.getMax() + stats.getMin();
	}
	

	private static Iterator<Long> getData() {
		return LectureFichiersUtils.getData("2020/input9.txt").map(Long::valueOf).iterator();
	}
	

}
