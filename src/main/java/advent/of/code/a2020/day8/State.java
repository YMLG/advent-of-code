package advent.of.code.a2020.day8;

public class State {
	private long acc = 0;
	private int pos = 0;
	
	public State addAcc(String acc) {
		this.acc += Integer.valueOf(acc);
		return this;
	}
	public State addPos(String pos) {
		this.pos += Integer.valueOf(pos);
		return this;
	}
	public State addPos(int pos) {
		this.pos +=  pos;
		return this;
	}
	public long getAcc() {
		return acc;
	}
	public int getPos() {
		return pos;
	}
	
}
