package assignment5;

public class AverageLinkage implements ClusterMethod {
	private DistanceMeasure distanceMethod;
	public double calculateDistance(Cluster cluster1, Cluster cluster2) {
		double result = 0;
		for (int i = 0; i < cluster1.getWidth(); i++) {
			for (int j = 0; j < cluster2.getWidth(); j++) {
				result += distanceMethod.calculateDistance(cluster1.getUnits().getUnit(i), cluster2.getUnits().getUnit(j));
			}
		}
		return result / (cluster1.getWidth() * cluster2.getWidth());
	}
	
	public AverageLinkage(DistanceMeasure method) {
		distanceMethod = method;
	}
	
	
}
