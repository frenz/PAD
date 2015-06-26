package assignment6;

import ui.DrawUserInterface;

public class Clusterer {
	private ClusterMethod method;
	private ClusterRow [] clusterRow;
	private int currentStep;
	private int steps;
	private View view;


	Clusterer(ClusterMethod method, ClusterRow clusterRow, DrawUserInterface userUI){
		view = new Cartesian(userUI);
		steps = clusterRow.getActualNumberOfCluster() - clusterRow.getNumberOfCluster() +1;
		this.clusterRow = new ClusterRow[steps];
		this.clusterRow[0] = clusterRow;
		this.method = method;
		currentStep = 0;
	}
	public int getSteps(){
		return steps;
	}


	private ClusterRow getMinStep(ClusterRow clusterRow){


		double min = Double.MAX_VALUE, temp;
		int source=0,dest=0;
		for (int i = 0; i < clusterRow.getNumberOfCluster(); i++) {
			for (int j = 0; j < i; j++) {
				temp = method.calculateDistance(clusterRow.getCluster(i), clusterRow.getCluster(j));
				if(min > temp){
					source=i;
					dest=j;
					min= temp;
				}          
			}
		}
		clusterRow.mergeClusters(source, dest);
		view.draw(clusterRow);
		return clusterRow;
	}
	public void nextStep(){
		if (currentStep<steps-1){
			clusterRow[currentStep+1] = getMinStep(clusterRow[currentStep]);
			currentStep++;
		}
	}


}
