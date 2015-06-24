package assignment3;

import java.util.Arrays;

public class UnitRow {

    private Unit[] units;
    
    private int numberOfUnits = 0;
    
    public UnitRow(int numberOfEntries) {
        units = new Unit[numberOfEntries];
    }
    
    public UnitRow(Unit unit) {
        units = new Unit[1];
        add(unit);
        numberOfUnits = 1;
    }
    
    public Unit get(int i) {
    	
        if (i >= numberOfUnits) {
            System.err.println("ERROR:");
            System.exit(-1);
        }
        return units[i];
    }
    public Unit[] getArray() {
        return units;
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
    	
        if (numberOfUnits <= units.length) {
        	 if (numberOfUnits == units.length) 
        		 inc();
        	units[numberOfUnits++] = i;
        }
        else{
        	System.err.println("ERROR:");
            System.exit(-1);
        }
    }
    public void inc() {
    	units = Arrays.copyOf(units, units.length+1);
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

	public Unit getUnit(int i) {
		return units[i];
	}
    
}
