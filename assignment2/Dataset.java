package assignment2;

import java.util.Arrays;


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
		return getMaximumOfIndex(getIndexOfIndex(s)-1);
	}
	public double  getMaximumOfIndex(int value){
		double max= unitRow.getValueAtPosition(value, 0);
		for (int i=0;i<numberOfRow;i++)
			max=unitRow.getValueAtPosition(value, i)>max?unitRow.getValueAtPosition(value, i):max;
			return max;
	}
	public double  getMinimumOfIndex(String s){
		return getMinimumOfIndex(getIndexOfIndex(s)-1);
	}
	public double  getMinimumOfIndex(int value){
		double min= Double.MAX_VALUE;
		for (int i=0;i<numberOfRow;i++)
			min=unitRow.getValueAtPosition(value, i)<min?unitRow.getValueAtPosition(value, i):min;
			return min;
	}

	private void filterDataFromIndex(String[] preselectedIndex) {
		for(int i=0;i<preselectedIndex.length;i++){
			unitRow.moveElement(getIndexOfIndex(preselectedIndex[i]),i);
		}
		unitRow.truncate(preselectedIndex.length);
	}

	private String[] getPreselectedIndex(){
		double limit;
		double[] standardDeviations = getStandardDeviation();
		double[] aux = standardDeviations.clone();
		Arrays.sort(aux);
		for(int i=0;i<aux.length/2;i++) {
			double temp = aux[i];
			aux[i] = aux[aux.length-(i+1)];
			aux[aux.length-(i+1)] = temp;
		}
		limit=aux[50];
		String[] indexPreselected = new String[50];
		for(int i=0, j=0;i<numberOfColoms && j<indexPreselected.length;i++)
			if (standardDeviations[i]>limit)
				indexPreselected[j++]=indexArray[i];
		return indexPreselected;
	}
	private double[] getStandardDeviation() {
		double[] result = new double[numberOfColoms];
		for (int i = 0; i < numberOfColoms; i++)
			result[i] = getStandardDeviationOfIndex(i);
		return result;
	}
	private double getStandardDeviationOfIndex(int i) {
		double result;
		double average = getAverageOfIndex(i);
		double sum = 0;
		for (int j = 0; j < numberOfRow; j++) 
			sum += Math.pow(unitRow.getValueAtPosition(i, j) - average, 2);
		result = (sum / (numberOfRow - 1));
		result = Math.sqrt(result);
		return result;
	}
	private double getAverageOfIndex(int i) {
		double result;
		double sum = 0;
		for (int j = 0; j < numberOfRow; j++)
			sum += unitRow.getValueAtPosition(i, j);
		result=sum/numberOfRow;
		return result;
	}
	public void normalization() {
		double min;
		double max;
		double maxSubMin;
		for (int i = 0; i < numberOfColoms; i++) {
			min = getMinimumOfIndex(i);
			max = getMaximumOfIndex(i);
			maxSubMin = max - min;
			for (int j = 0; j < numberOfRow; j++)
				unitRow.setValueAtPosition(i, j,((unitRow.getValueAtPosition(i, j)-min)/maxSubMin));
		}
	}
	public void preselection() {
		if (indexArray.length>50){
			String[] preselectedIndex = getPreselectedIndex();
			filterDataFromIndex(preselectedIndex);
			indexArray=preselectedIndex;
			numberOfColoms=50;
		}
		System.out.println("Variables after preselection:");
		System.out.print(indexArray[0]);
		for(int i=1;i<indexArray.length;i++)
			System.out.print(", "+indexArray[i]);
	}

}
