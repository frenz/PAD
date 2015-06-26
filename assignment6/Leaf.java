package assignment6;

public class Leaf implements Cluster {
	private Unit unit;

	public int getDepth() {
		return 0;
	}

	public int getWidth() {
		return 1;
	}

	public UnitRow getUnits() {
		UnitRow unitRow = new UnitRow(unit);
		return unitRow;
	}

	public boolean hasChildren() {
		return false;
	}
	
	Leaf(Unit unit){
		this.unit = unit;
	}

}
