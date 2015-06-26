package assignment6;

import java.io.PrintStream;
import java.util.Scanner;

import ui.UIAuxiliaryMethods;
import ui.Colour;
import ui.UserInterfaceFactory;
import ui.DrawUserInterface;


public class Clustering {
	
	private final int WIDTH_UI = 1024;
	private final int HEIGHT_UI = 768;
	DrawUserInterface userUI;
	PrintStream out;
	Clustering() {
		UIAuxiliaryMethods.askUserForInput();
		userUI = UserInterfaceFactory.getDrawUI(WIDTH_UI, HEIGHT_UI);
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
        DistanceMeasure measure = new Euclidean();
		//DistanceMeasure measure = new Manhattan();
		//DistanceMeasure measure = new Pearson();
		
        //ClusterMethod clusterMethod = new CompleteLinkage(measure);
		//ClusterMethod clusterMethod = new SingleLinkage(measure);
		ClusterMethod clusterMethod = new AverageLinkage(measure);
		Clusterer clusterer = new Clusterer(clusterMethod, clusterRow, userUI);
		for(int i=0;i<27;i++){
			clusterer.nextStep();
			try {
			    Thread.sleep(100);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}

		in.close();
	}

	public static void main(String[] argv) {
		new Clustering().start();
	}
}
