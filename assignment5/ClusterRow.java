package assignment5;


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
			//System.out.println(i+"-------------"+data.getUnit(i).getNumberRow().get(0));
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
	public void printDistanceTwoCluster() {
		SingleLinkage singleLinkage;
		CompleteLinkage completeLinkage;
		AverageLinkage averageLinkage;

		singleLinkage = new SingleLinkage( new Euclidean());
		System.out.printf("Euclidean + SingleLinkage:	%.6f\n",singleLinkage.calculateDistance(rowsCluster[0], rowsCluster[1]));
		
		averageLinkage = new AverageLinkage( new Euclidean());
		System.out.printf("Euclidean + AverageLinkage:	%.6f\n",averageLinkage.calculateDistance(rowsCluster[0], rowsCluster[1]));
		
		completeLinkage = new CompleteLinkage( new Euclidean());
		System.out.printf("Euclidean + CompleteLinkage:	%.6f\n",completeLinkage.calculateDistance(rowsCluster[0], rowsCluster[1]));
		
		singleLinkage = new SingleLinkage( new Manhattan());
		System.out.printf("Manhattan + SingleLinkage:	%.6f\n",singleLinkage.calculateDistance(rowsCluster[0], rowsCluster[1]));
		
		averageLinkage = new AverageLinkage( new Manhattan());
		System.out.printf("Manhattan + AverageLinkage:	%.6f\n",averageLinkage.calculateDistance(rowsCluster[0], rowsCluster[1]));
		
		completeLinkage = new CompleteLinkage( new Manhattan());
		System.out.printf("Manhattan + CompleteLinkage:	%.6f\n",completeLinkage.calculateDistance(rowsCluster[0], rowsCluster[1]));
		
		singleLinkage = new SingleLinkage( new Pearson());
		System.out.printf("Pearson + SingleLinkage:	%.6f\n",singleLinkage.calculateDistance(rowsCluster[0], rowsCluster[1]));
		
		averageLinkage = new AverageLinkage( new Pearson());
		System.out.printf("Pearson + AverageLinkage:	%.6f\n",averageLinkage.calculateDistance(rowsCluster[0], rowsCluster[1]));
		
		completeLinkage = new CompleteLinkage( new Pearson());
		System.out.printf("Pearson + CompleteLinkage:	%.6f\n",completeLinkage.calculateDistance(rowsCluster[0], rowsCluster[1]));
	}
}
