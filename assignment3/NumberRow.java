package assignment3;

import java.util.Arrays;


public class NumberRow {

	private double[] rowsArray;
	private int numberOfRows = 0;

	public NumberRow(int colomns) {
		rowsArray = new double[colomns];
	}


	public double get(int i) { 
		return rowsArray[i];
	}

	public void set(int i, double d) { 
		if (i<=numberOfRows) {
			rowsArray[i] = d;
		}
		else{
			System.err.println("ERROR: ");
			System.exit(-1);
		}
	}

	public void add(double d) {
		if (numberOfRows < rowsArray.length) {
			rowsArray[numberOfRows++] = d;
		}
		else{
			System.err.println("ERROR: ");
			System.exit(-1);
		}
	}

	public int size() {
		return numberOfRows;
	}


	public void truncate(int length) {
		rowsArray = Arrays.copyOf(rowsArray, length);	
	}


	public double getMax() {
		double result = Double.MIN_VALUE;
		for(double item:rowsArray)
			result=item>result?item:result;
		return result;
	}
}