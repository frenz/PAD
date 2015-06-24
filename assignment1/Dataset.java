package assignment1;

public class Dataset {
	private int numberOfCluster, numberOfRow, numberOfColoms;
	private String type;
	private String[] indexArray;
	private UnitRow unitRow;

	
	Dataset(int numberOfCluster, int numberOfRow, int numberOfColoms, String index, String[] indexArray , UnitRow unitRow) {
		this.numberOfCluster = numberOfCluster;
		this.numberOfRow = numberOfRow;
		this.numberOfColoms = numberOfColoms;
		this.type = index;
		this.indexArray = indexArray;
		this.unitRow =unitRow;
	}

	public int getNumberOfCluster() {
		return numberOfCluster;
	}
	
	public int getNumberOfRow() {
		return numberOfRow;
	}
	
	public int getNumberOfColoms() {
		return numberOfColoms;
	}
	
	public String getType() {
		return type;
	}
	
	public String[] getIndexArray() {
		return indexArray;
	}
	
	public void setNumberOfCluster(int numberOfCluster) {
		this.numberOfCluster = numberOfCluster;
	}
	
	public void setNumberOfRow(int numberOfRow) {
		this.numberOfRow = numberOfRow;
	}
	
	public void setNumberOfColoms(int numberOfColoms) {
		this.numberOfColoms = numberOfColoms;
	}
	
	public void setIndex(String type) {
		this.type = type;
	}
	
	public void setIndexArray(String[] indexArray) {
		this.indexArray = indexArray;
	}

	public String  getIndexAtPosition(int i){
		return indexArray[i];
	}
	public int  getIndexOfIndex(String s){
		for (int i=0;i<indexArray.length;i++)
			if(indexArray[i].equals(s))
				return i;
		return -1;
	}
	public double  getMaximumOfIndex(String s){
		return getMaximumOfIndex(getIndexOfIndex(s));
	}
	public double  getMaximumOfIndex(int value){
		double max= Double.MIN_VALUE;
		for (int i=0;i<numberOfRow;i++)
			max=unitRow.getValueAtPosition(value, i)>max?unitRow.getValueAtPosition(value, i):max;
			
		return max;
	}
}
