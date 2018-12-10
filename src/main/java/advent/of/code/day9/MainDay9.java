package advent.of.code.day9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MainDay9 {

	private static int nbPlayer = 438;
	private static int maxMarble = 71626;


	public static void main(String[] args) {
		System.out.println("Question 1 - "+ getReponseQuestion1());
		System.out.println("Question 2 - "+ getReponseQuestion2());

	}

	private static Integer getReponseQuestion1() {
		List<Integer> circle = new LinkedList<>(Collections.singleton(0));
		List<Integer> players = new ArrayList<>(Collections.nCopies(nbPlayer, 0));
		int currentPosition = 1;
		for(int i = 1; i< maxMarble+1; i++) {
			if( i % 23 > 0 ) {
				currentPosition = 1 + (currentPosition + 1) % circle.size();
				circle.add(currentPosition,i);
			}else {
				int player = i % nbPlayer;
				int nbPointPlayers = players.get(player);
				nbPointPlayers+=i;
				int toRemove = currentPosition-7;
				if(toRemove <0) {
					toRemove += circle.size();
				}
				nbPointPlayers+=circle.get(toRemove);
				players.set(player, nbPointPlayers);
				circle.remove(toRemove);
				currentPosition = toRemove;
			}
		}
		return Collections.max(players);
	}

	private static Long getReponseQuestion2() {
		LinkedList<Integer> working = new LinkedList<>(Collections.singleton(0));
		LinkedList<Integer> sleeping = new LinkedList<>();
		List<Long> players = new ArrayList<>(Collections.nCopies(nbPlayer, 0L));
		for(int i = 1; i< maxMarble*100+1; i++) {
			if(sleeping.isEmpty()) {
				working.add(working.poll());
				working.add(i);
			}
			else if( i % 23 > 0 ) {
				working.add(sleeping.poll());
				working.add(i);
				sleeping.add(working.poll());
			}else {
				int player = i % nbPlayer;
				Long nbPointPlayers = players.get(player);
				nbPointPlayers+=i;
				nbPointPlayers+=working.poll();
				players.set(player, nbPointPlayers);
				for(int j=0; j<6; j++) {
					sleeping.addFirst(working.pollLast());
					working.addFirst(sleeping.pollLast());
				}

			}
			if(working.size()>8) {
				sleeping.add(working.poll());
			}
		}
		return Collections.max(players);
	}
}
