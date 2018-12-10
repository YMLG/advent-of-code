package advent.of.code.day8;

import java.util.ArrayList;
import java.util.List;

public class Node {
	
	private List<Integer> metadata = new ArrayList<>();
	
	private List<Node> childs = new ArrayList<>();

	private Integer sumMetadatas = 0;
	
	public List<Integer> getMetadata() {
		return metadata;
	}

	public void setMetadata(List<Integer> metadata) {
		this.metadata = metadata;
	}

	public List<Node> getChilds() {
		return childs;
	}

	public void setChilds(List<Node> childs) {
		this.childs = childs;
	}
	
	
	public Integer getSumMetadata() {
		return sumMetadatas;
	}
	
	public Integer getValue() {
		if(childs.isEmpty()) {
			return getSumMetadata();
		}
		return metadata.stream()
					.filter(i -> i > 0 && i<childs.size()+1)
					.map(i -> childs.get(i-1).getValue())
				    .mapToInt(Integer::valueOf)
				    .sum();
	}


	public void addChild(Node n) {
		childs.add(n);
		sumMetadatas += n.getSumMetadata();
	}
	
	public void addMetadata(Integer i) {
		metadata.add(i);
		sumMetadatas += i;
	}
}
