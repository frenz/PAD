package assignment2;

public class UnitRow {

    private Unit[] units;
    
    private int numberOfUnits = 0;
    
    public UnitRow(int numberOfEntries) {
        units = new Unit[numberOfEntries];
    }
    
    public Unit get(int i) {
    	
        if (i >= numberOfUnits) {
            System.err.println("ERROR:");
            System.exit(-1);
        }
        return units[i];
    }
    public Double getValueAtPosition(int i, int row) {
    	
        if (row >= numberOfUnits) {
            System.err.println("ERROR:");
            System.exit(-1);
        }
        return get(row).getValueAtPosition(i);
    }
    public void setValueAtPosition(int i, int row, Double d) {
    	
        if (row >= numberOfUnits) {
            System.err.println("ERROR:");
            System.exit(-1);
        }
        get(row).setValueAtPosition(i,d);
    }
    
    public void set(int i, Unit unit) {
        if (i >= numberOfUnits) {
            System.err.println("ERROR:");
            System.exit(-1);
        }

        units[i] = unit;
    }
    
    public void add(Unit i) {
        if (numberOfUnits < units.length) {
        	units[numberOfUnits++] = i;
        }
        else{
        	System.err.println("ERROR:");
            System.exit(-1);
        }
    }

    public int size() {
        return numberOfUnits;
    }

	public void moveElement(int source, int dest) {
		for(Unit unit:units){
			unit.setValueAtPosition(dest, unit.getValueAtPosition(source));
		}
		
	}

	public void truncate(int length) {
		for(Unit unit:units){
			unit.truncate(length);
		}
		
	}
    
}
