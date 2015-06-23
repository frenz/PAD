package assignment3;

public class Unit {
    private String label;
    private NumberRow numberRow;

    public Unit(String label, NumberRow numberRow ) {
    	this.label = label;
    	this.numberRow = numberRow;
    }

    public String getLabel() {
        return label;
    }

    public NumberRow getNumberRow() {
        return numberRow;
    }
    public Double getValueAtPosition(int i) {
    	return numberRow.get(i);
    }
    public void setLabel(String label) {
        this.label = label;
    }

    public void setNumberRow(NumberRow numberRow) {
        this.numberRow = numberRow;
    }
    public void setValueAtPosition(int i, Double d) {
    	numberRow.set(i, d);
    }

	public void truncate(int length) {
		numberRow.truncate(length);
		
	}
}
