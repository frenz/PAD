package assignment6;

import ui.Colour;
import ui.DrawUserInterface;


public class Dendogram implements View {
	private final int WIDTH_UI = 1024;
	private final int HEIGHT_UI = 768;
	private DrawUserInterface userUI;
	int vertexSize, depthSize;
	int numberOfUnits;
	int nthUnit, startX, startY, endX, endY;

	public Dendogram(DrawUserInterface userUI, ClusterRow clusterRow) {
		this.userUI = userUI;
		this.numberOfUnits = clusterRow.getNumberOfRow();

		startX = WIDTH_UI/20;
		startY = HEIGHT_UI/20;
		endX = WIDTH_UI - WIDTH_UI/10; 
		endY = HEIGHT_UI - HEIGHT_UI/40;

		this.vertexSize = (endY-startY) / numberOfUnits;
		this.vertexSize -= (1-vertexSize%2);
		this.depthSize = (endX-startX)/(numberOfUnits - clusterRow.getNumberOfCluster() + 1);
	}

	public void draw(ClusterRow clusters) {
		userUI.clear();
		nthUnit = 0;
		for (int i = 0; i < clusters.getNumberOfCluster(); i++) {
			draw(clusters.getCluster(i), 0);
		}
		userUI.showChanges();
	}

	private int draw(Cluster cluster, int parentDepth) {
		if (cluster.hasChildren()) {
			return drawNode((Node)cluster,parentDepth);
		}
		else {
			return drawLeaf((Leaf)cluster,parentDepth);
		}
	}

	private int drawNode(Node node, int parentDepth){
		Cluster child1 =  node.getChild(0);
		Cluster child2 =  node.getChild(1);
		int y1 = draw(child1, node.getDepth());
		int y2 = draw(child2, node.getDepth());
		int x = depthToX(node.getDepth());
		userUI.drawLine(x, y1, x, y2, getRandomColour());
		int x1 = depthToX(parentDepth);
		int x2 = depthToX(node.getDepth());
		int y = (y1 + y2) / 2;
		userUI.drawLine(x1, y, x2, y, getRandomColour());
		return y;
	}

	private int drawLeaf(Leaf leaf, int parentDepth){
		UnitRow unitRow = leaf.getUnits();
		int y = drawUnit(unitRow.getUnit(0), getRandomColour(), parentDepth);
		nthUnit++;
		return y;
	}

	private int drawUnit(Unit u, Colour c, int parentDepth) {
		int x = endX;
		int y = startY +  nthUnit * (endY-startY)/numberOfUnits ;
		String label = "-" + u.getLabel();
		userUI.drawLine(depthToX(parentDepth), y, x - vertexSize / 2, y, getRandomColour());
		userUI.drawCircle(x, y, vertexSize, vertexSize, c, true);
		userUI.drawText(x + vertexSize, y - vertexSize / 5, label, getRandomColour());

		return y;
	}

	private int depthToX(int x) {
		if (x == 0) { 
			return startX; 
		}
		return endX - depthSize * (x + 1);
	}
	private Colour getRandomColour(){
		int value1 = (int) ((int)255*Math.random());
		int value2 = (int) ((int)255*Math.random());
		int value3 = (int) ((int)255*Math.random());
		Colour colour= new Colour(value1, value2, value3);
		return colour;
	}

}
