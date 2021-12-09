package advent.of.code.a2021.day9;

import java.util.Objects;

public class Cellule {

	private Integer x;
	private Integer y;
	private Integer value;
	
	public Cellule(Integer x, Integer y, Integer value) {
		this.x = x;		
		this.y = y;
		this.value = value;
	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}

	public Integer getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cellule other = (Cellule) obj;
		return Objects.equals(x, other.x) && Objects.equals(y, other.y);
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
}
