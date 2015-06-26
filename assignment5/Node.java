package assignment5;



public class Node  implements Cluster {
	private Cluster leftChild, rightChild;
	
	public int getDepth() {
		return (leftChild.getDepth() >= rightChild.getDepth())?leftChild.getDepth()+1:rightChild.getDepth()+1;
	}

	public int getWidth() {
		return leftChild.getWidth()+rightChild.getWidth();
	}

	public UnitRow getUnits() {
		UnitRow result = leftChild.getUnits();
		Unit[] right = rightChild.getUnits().getArray();
		for(Unit item:right)
			result.add(item);
	    return  result;
	}

	public boolean hasChildren() {
		return false;
	}
	

	public Node(Cluster cluster, Cluster cluster2) {
	    leftChild = cluster;
	    rightChild = cluster2;
	}
}
