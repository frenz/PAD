package assignment3;

import java.io.PrintStream;
import java.util.Scanner;
import ui.UIAuxiliaryMethods;


public class Clustering {
	PrintStream out;
	Clustering() {
		UIAuxiliaryMethods.askUserForInput();
		out = new PrintStream(System.out);
	}
	private static int readInt(String input) {
		Scanner in = new Scanner(input);
		if (!in.hasNextInt()){
			System.err.println("ERROR:");
            System.exit(-1);
		}
		int result = in.nextInt();
		in.close();
        return result;
    }
	private static String[] readStrings(String input, int numberOfColoms) {
		Scanner in = new Scanner(input);
		String[] result = new String[numberOfColoms+1];
		for(int i=0;i<=numberOfColoms;i++){
			if (!in.hasNext()){
				System.err.println("ERROR:");
	            System.exit(-1);
			}
			result[i] = in.next();;
		}
		in.close();
		return result;
    }

    private static UnitRow readData(Scanner in, int numberOfColoms, int numberOfRows) {
		UnitRow result = new UnitRow(numberOfRows);
		Unit unit;
		String label;
		NumberRow numberRow;
        for (int i = 0; i < numberOfRows; i++) {
        	if (!in.hasNext()){
				System.err.println("ERROR:");
	            System.exit(-1);
			}
        	label = in.next();
        	numberRow = new NumberRow(numberOfColoms);
        	for (int j = 0; j < numberOfColoms; j++) {
            	if (!in.hasNextDouble()){
    				System.err.println("ERROR:");
    	            System.exit(-1);
    			}
        		numberRow.add(in.nextDouble());
            }
        	unit = new Unit(label,numberRow);
        	result.add(unit);
        }
        return result;
    }

	void start() 
	{
		Scanner in = new Scanner(System.in);
        int numberOfClusters = readInt(in.nextLine());
        int numberOfRows = readInt(in.nextLine());
        int numberOfColoms = readInt(in.nextLine());
        String[] headers = readStrings(in.nextLine(),  numberOfColoms);
        UnitRow data = readData(in,  numberOfColoms,  numberOfRows);
        Dataset dataset = new Dataset(numberOfClusters, numberOfRows, numberOfColoms, headers, data);
        dataset.normalization();
        dataset.preselection();
		in.close();
	}

	public static void main(String[] argv) {
		new Clustering().start();
	}
}
