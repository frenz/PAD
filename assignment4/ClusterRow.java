package assignment4;


public class ClusterRow {
	private Cluster[] rowsCluster;
	private int numberOfRows = 0;

	private int numberOfColoms;
	private int numberOfRow;
	private int numberOfCluster;



	ClusterRow(Dataset data){
		numberOfColoms  = data.getNumberOfColoms();
		numberOfRow = data.getNumberOfRow();
		numberOfCluster = data.getNumberOfCluster();
		rowsCluster = new Cluster[numberOfRow];
		for(int i = 0; i < numberOfRow; i++){
			Cluster leaf = new Leaf(data.getUnit(i));
			rowsCluster[i]=leaf;
			numberOfRows=i;
		}
	}
	double getMax(){
		double max = -Double.MAX_VALUE;
		for(int i = 0; i < numberOfRows; i++)
			max = max > getMaxByIndex(i)?max:getMaxByIndex(i);
		return max;
	}

	double getMaxByIndex(int i){
		double max = -Double.MAX_VALUE;
		for(int j = 0; j < rowsCluster[i].getUnits().size(); j++)
			max = (max > rowsCluster[i].getUnits().getUnit(j).getMax())?max:rowsCluster[i].getUnits().getUnit(j).getMax();
		return max;
	}

	void printMax(){
		for(int i = 0; i < numberOfRows; i++)
			printMaxByIndex(i);
	}
	
	void printMaxByIndex(int i){
		System.out.printf("The cluster number %d has maximum value: %f \n",i, getMaxByIndex(i));
	}
}
