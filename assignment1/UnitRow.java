package assignment1;

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
    public Double getValueAtPosition(int i, int row) {
    	
        if (row >= numberOfUnits) {
            System.err.println("ERROR:");
            System.exit(-1);
        }
        return get(row).getValueAtPosition(i);
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
    
}
