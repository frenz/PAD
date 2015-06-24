package assignment4;

public class Pearson implements DistanceMeasure {

	public double calculateDistance(Unit unit1, Unit unit2) {
		double average1 = averageValue(unit1);
		double average2 = averageValue(unit2);
		double standardDeviation1 = standardDeviation(unit1);
		double standardDeviation2 = standardDeviation(unit2);
		double pearsonCorrelation = 0;
		for(int i = 0; i < unit1.getNumberRow().size(); i++){
			pearsonCorrelation += ((unit1.getValueAtPosition(i) - average1)/standardDeviation1)*((unit2.getValueAtPosition(i) - average2)/standardDeviation2);
		}
		return Math.abs(1-pearsonCorrelation/(unit1.getNumberRow().size()-1));
	}

	private double averageValue(Unit unit){
		double result = 0;
		for(int i = 0; i < unit.getNumberRow().size(); i++)
			result = result + unit.getValueAtPosition(i);
		return result/unit.getNumberRow().size();
	}
	
	private double standardDeviation(Unit unit){
		double result = 0;
		for(int i = 0; i < unit.getNumberRow().size(); i++){
			result += Math.pow(unit.getValueAtPosition(i) - averageValue(unit), 2);
		}
		return Math.sqrt(result)/(unit.getNumberRow().size()-1);
	}
}
