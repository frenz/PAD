package assignment6;

public class Manhattan implements DistanceMeasure {
	public double calculateDistance(Unit unit1, Unit unit2) {
		double result = 0;
        for (int i = 0; i < unit1.getNumberRow().size(); i++) {
        	result += Math.abs(unit1.getValueAtPosition(i) - unit2.getValueAtPosition(i));
        }
        return result;
    }
}
