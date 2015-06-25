package assignment4;

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
			result[i] = in.next();
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
        String[] all = readStrings(in.nextLine(),  numberOfColoms);
        String type = all[0];
        String[] headers = new String[all.length-1];
        for(int i=0; i<headers.length; i++)
        	headers[i] = all[i+1];
        UnitRow data = readData(in,  numberOfColoms,  numberOfRows);
        Dataset dataset = new Dataset(numberOfClusters, numberOfRows, numberOfColoms, type, headers, data);
        dataset.normalization();
        dataset.preselection();
        ClusterRow clusterRow = new ClusterRow(dataset);
		clusterRow.printDistanceTwoCluster();
		
		in.close();
	}

	public static void main(String[] argv) {
		new Clustering().start();
	}
}
