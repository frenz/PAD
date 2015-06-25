package assignment5;

public class SingleLinkage implements ClusterMethod {
    private DistanceMeasure distanceMethod;
    public double calculateDistance(Cluster cluster1, Cluster cluster2) {
        double result = Double.MAX_VALUE;
        for (int i = 0; i < cluster1.getWidth(); i++) {
        	for (int j = 0; j < cluster2.getWidth(); j++) {
            	double temp = distanceMethod.calculateDistance(cluster1.getUnits().getUnit(i), cluster2.getUnits().getUnit(j));
            	result = (temp < result)?temp:result;
            }
        }
        return result;
    }
    public SingleLinkage(DistanceMeasure method) {
		distanceMethod = method;
	}
}
