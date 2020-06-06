package edu.mid.readWriteDisplayTriangles;

/*This class performs some math to convert the file/record data into fields useful for generating triangles
	may add color field
*/

import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import javafx.scene.transform.Translate;

class Triangle extends Polygon{
	private int myID;
	private double x, y, angle, adjacent, opposite, hypoteneuse;
	private char direction;
	//private Polygon polygon;
	private String colorString;
	private String name;
	
	Triangle(int myID, double x, double y, char direction, double angle, double adjacent, String name) {
		this.myID = myID;
		this.x = x;
		this.y = y;
		this.direction = direction; 
		this.angle = angle;
		this.adjacent = adjacent;
		this.opposite = Math.tan(Math.toRadians(angle)) * adjacent;
		this.hypoteneuse = adjacent / Math.cos(Math.toRadians(angle));
		this.name = name;
		generatePolygon();
		//this.fillColor = Color.web(CONST.RED);
	
	}
	// update from polygon properties 
	public double getX() {
		if(Debug.isDebug1() == true) { System.out.println("Triangle.getX() = " + x ); }
		return x;
	}
	public double getY() {
		if(Debug.isDebug1() == true) { System.out.println("Triangle.getY() = " + y ); }
		return y;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getOpposite() {
		return opposite;
	}
	public char getDirection() {
		return direction;
	}
	public void setDirection(char dir) {
		if(Debug.isDebug1() == true) { System.out.println("setDirection in triangle. old val: " + direction + " new val: " + dir); }
		if(direction == 'u') {
			switch(dir) {
				case 'l':
					rotate90Left(1);
					break;
				case 'd':
					rotate90Left(2);
					break;
				case 'r':
					rotate90Left(3);
					break;
			}
		}
		else if(direction == 'l') {
			switch(dir) {
				case 'd':
					rotate90Left(1);
					break;
				case 'r':
					rotate90Left(2);
					break;
				case 'u':
					rotate90Left(3);
					break;
			}
		}
		else if(direction == 'd') {
			switch(dir) {
				case 'r':
					rotate90Left(1);
					break;
				case 'u':
					rotate90Left(2);
					break;
				case 'l':
					rotate90Left(3);
					break;
			}
		}
		else if(direction == 'r') {
			switch(dir) {
				case 'u':
					rotate90Left(1);
					break;
				case 'l':
					rotate90Left(2);
					break;
				case 'd':
					rotate90Left(3);
					break;
			}
		}		
	}
	
	//recursive rotate, had to account for this weird coordinate system to implement the rotations I wanted
	public void rotate90Left(int rotations) {
		if(Debug.isDebug1() == true) { System.out.println("Recursive rotation called in Triangle, rotation value: " + rotations); }
		if(rotations <= 0) { return; }
		switch(direction) {
			case 'u':
				x -= opposite;
				y -= opposite;
				direction = 'l';
				break;
			case 'l':
				y += adjacent;
				x -= opposite;
				direction = 'd';
				break;
			case 'd':
				x += adjacent;
				direction = 'r';
				break;
			case 'r':
				y -= opposite;
				direction = 'u';
				break;
		}
		if(Debug.isDebug1() == true) { System.out.println("New x: " + x + " Newy: " + y); }
		generatePolygon();
		rotate90Left(--rotations);		
	}
	
	public double getHypoteneuse() {
		return hypoteneuse;
	}
/* 	public void move(double x0, double y0) {
		if(Debug.isDebug1() == true) {
			System.out.println("Move triangle: " + x0 + " " + y0);
			System.out.println("This Triangle instance x: " + x + " y:" + y);
			System.out.println("LayoutX is: " + getLayoutX() + "LayoutY is: " + getLayoutY());
		}
		x += x0;
		y += y0;
		generatePolygon();
	} */
	
	public void mouseMove(double xOff, double yOff) {
		x = x + xOff;
		y = y + yOff;
		relocate(x,y);
	}
	
	private void generatePolygon() {
		double x2, y2, x3, y3;
		if(direction == 'r') { //right
			x2 = x;
			y2 = y + adjacent;
			x3 = x2 + opposite;
			y3 = y2;
		}
		else if(direction == 'u') { //up
			x2 = x + adjacent;
			y2 = y;
			x3 = x2;
			y3 = y2 - opposite;
		}
		else if(direction == 'l') { //left
			x2 = x;
			y2 = y - adjacent;
			x3 = x2 - opposite;
			y3 = y2;
		}
		else { // down and default if bad argument
			x2 = x - adjacent;
			y2 = y;
			x3 = x2;
			y3 = y2 + opposite;
		}
		//Debugging polygons - i wasn't passing the angle parameter properly
		if(Debug.isDebug1() == true) {System.out.println("DebgPoly-x1:" + Math.round(x) +
								   "-y1:" + Math.round(y) +
								   "-x2:" + Math.round(x2) +
								   "-y2:" + Math.round(y2) +
								   "-x3:" + Math.round(x3) +
								   "-y3:" + Math.round(y3) +
								   "-ang:" + Math.round(angle) +
								   "-adj:"+ Math.round(adjacent) +
								   "-opp:"+ Math.round(opposite) +
									"-dir:"+ direction);}
		//setAll on observable list clears the old list 
		getPoints().setAll( new Double[] { x,y,x2,y2,x3,y3} );
		relocate(x,y);
		//setFill(Color.web(colorString));
	}
	
	public Record makeRecord() {
		return new Record(myID, x, y, direction, angle, adjacent, name, colorString);
	}
/* 	public Record makeRecord() {

		double x1,x2,x3,y1,y2,y3, translateX, translateY;
		translateX = getTranslateX();
		translateY = getTranslateY();
		if(Debug.isDebug1()==true) {
			System.out.println("Reset from polygon called in Triangle. Old Points: ");
			_debug_printInfo();
			System.out.println("Translate X: " + translateX + " TranslateY: " + translateY);
		}
		x1 = getPoints().get(0).doubleValue() + translateX;
		y1 = getPoints().get(1).doubleValue() + translateY;
		x2 = getPoints().get(2).doubleValue() + translateX;
		y2 = getPoints().get(3).doubleValue() + translateY;
		x3 = getPoints().get(4).doubleValue() + translateX;
		y3 = getPoints().get(5).doubleValue() + translateY;
		if(Debug.isDebug5() == true) {
			System.out.println("points from poly: x1: " + x1
								+ " y1:" + y1 + " x2 " + x2 
								+ " y2:" + y2 + " x3 " + x3
								+ " y3:" + y3);
		}
		if(x2 > x1 && y3 > y1) direction = 'u';
		else if(y2 < y1 && x3 > x2) direction = 'r';
		else if(y2 < y1 && x3 < x2) direction = 'l';
		else direction = 'd';
		switch(direction) {
			case 'u':
				adjacent = x2 - x1;
				break;
			case 'l':
				adjacent = y1 - y2;
				break;
			case 'd':
				adjacent = x1 - x2;
				break;
			case 'r':
				adjacent = y2 - y1;
				break;
		}
		if(Debug.isDebug1()==true) {
			System.out.println("New Points: ");
			_debug_printInfo();
			System.out.println("Adjecent: " + adjacent + " Opp: " + opposite);
		}
		//calc angle
		//convert color?
		return new Record(myID, x1, y1, direction, angle, adjacent, name, colorString);
		
	} */
	
	public void setColor(String colorString) {
		this.colorString = colorString;
		setFill(Color.web(colorString));
		if(Debug.isDebug3() == true) {
			System.out.println("Triangle filled with color + " + colorString);
		}
	}
	
	public String _debugString_() {
		String s = "Triangle _debugString_ called. Name: " + name +
		"\n Triangle x: " + x +
		"\n Triangle point 0x :" + getPoints().get(0) +
		"\n Triangle y: " + y + 
		"\n Triangle point 1x :" + getPoints().get(1);
		s = "Triangle name : " + name;
		return s;
	}
	public void _debug_printInfo() {
		System.out.println(
		" x:" + this.x +
		" y:" + this.y +
		" dir:" + direction +
		" ang:" + angle +
		" adj:" + adjacent +
		" opp:" + opposite +
		" hyp:" + hypoteneuse +
		" nm:" + name
		);
	}
}