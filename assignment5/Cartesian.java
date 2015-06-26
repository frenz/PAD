package assignment5;
import ui.Colour;
import ui.DrawUserInterface;

public class Cartesian implements View{

	private final int WIDTH_UI = 1024;
	private final int HEIGHT_UI = 768;
	final private int DIAMETER_CIRCLE = HEIGHT_UI/50;
	private final int FATT_X =(WIDTH_UI-DIAMETER_CIRCLE);
	private final int FATT_Y =(HEIGHT_UI-DIAMETER_CIRCLE);




	private Colour AXIS_COLOUR;
	private DrawUserInterface userUI;

	Cartesian(DrawUserInterface userUI){
		this.userUI = userUI;
		AXIS_COLOUR = new Colour(0,0,0);
	}

	public void draw(ClusterRow clusters) {

		userUI.clear();
		drawAxis();
		for(int i=0;i<clusters.getActualNumberOfCluster();i++){
			double xValue=0;
			double yValue=0;
			double maxDistance=0;
			double xMin=WIDTH_UI;
			double xMax=0;
			double yMin=HEIGHT_UI;
			double yMax=0;
			double xCenter=0;
			double yCenter=0;
			Cluster cluster=clusters.getCluster(i);
			Colour colourCluster = getRandomColour();
			for(int j=0;j<cluster.getWidth();j++){

				xValue=cluster.getUnits().getValueAtPosition(0, j);
				xMin = (xMin<xValue)?xMin:xValue;
				xMax = (xMax>xValue)?xMax:xValue;
				yValue=cluster.getUnits().getValueAtPosition(1, j);
				yMin = (yMin<yValue)?yMin:yValue;
				yMax = (yMax>yValue)?yMax:yValue;
				draw(xValue,yValue,DIAMETER_CIRCLE,DIAMETER_CIRCLE,colourCluster,true);

			}
			Colour colourCircle = getRandomColour();
			xCenter=((xMax-xMin)/2)+xMin;
			yCenter=(yMin+yMax)/2;
			maxDistance=maxDistancefromCenter( cluster, xCenter, yCenter);
			draw(xCenter,yCenter,(int)(maxDistance*FATT_X*2),(int)(maxDistance*FATT_X*2),colourCircle,false);
		}
		userUI.showChanges();

	}

	private double maxDistancefromCenter(Cluster cluster, double xCenter, double yCenter) {
		double xValue1=0;
		double yValue1=0;
		double result = Double.MIN_VALUE;
		for(int i=0;i<cluster.getWidth();i++){
			xValue1=cluster.getUnits().getValueAtPosition(0, i);
			yValue1=cluster.getUnits().getValueAtPosition(1, i);
			double tempDiametro = Math.sqrt(Math.pow((xValue1-xCenter), 2) + Math.pow((yValue1-yCenter), 2));
			result= result>tempDiametro?result:tempDiametro;
		}
		return result;
	}


	private void draw(double xValue, double yValue, int xSize, int ySize, Colour colour, boolean colored) {
		userUI.drawCircle((int) (xValue*FATT_X+(DIAMETER_CIRCLE)), (int) (yValue*FATT_Y+(DIAMETER_CIRCLE)) , xSize, ySize, colour, colored);
	}

	private void drawAxis(){
		userUI.drawLine(DIAMETER_CIRCLE, DIAMETER_CIRCLE, WIDTH_UI-DIAMETER_CIRCLE, DIAMETER_CIRCLE, AXIS_COLOUR);
		userUI.drawLine(DIAMETER_CIRCLE, DIAMETER_CIRCLE, DIAMETER_CIRCLE, HEIGHT_UI-DIAMETER_CIRCLE, AXIS_COLOUR);
	}

	private Colour getRandomColour(){
		int value1 = (int) ((int)255*Math.random());
		int value2 = (int) ((int)255*Math.random());
		int value3 = (int) ((int)255*Math.random());
		Colour colour= new Colour(value1, value2, value3);
		return colour;
	}
}
