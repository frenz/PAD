package assignment4;

public class Node  implements Cluster {
	private Cluster leftChild, rightChild;
	
	public int getDepth() {
		return (leftChild.getDepth() >= rightChild.getDepth())?leftChild.getDepth()+1:rightChild.getDepth()+1;
	}

	public int getWidth() {
		int baseSize=0;
		return leftChild.getWidth()+rightChild.getWidth()+baseSize;
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
	
	Node(Cluster leftChild, Cluster rightChild){
	    this.leftChild = leftChild;
	    this.rightChild = rightChild;
	}
}
