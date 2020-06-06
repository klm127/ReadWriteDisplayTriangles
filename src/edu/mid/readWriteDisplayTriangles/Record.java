package edu.mid.readWriteDisplayTriangles;

import java.io.*;
import java.awt.*;
import java.awt.event.*;

class Record {
	private Triangle triangle;
	private int id;
	private double x, y, angle, adjacent;
	private char direction;
	private String name;
	private String colorString;
	
	//full arg Constructor
	Record( int id, double x, double y, char direction, double angle, double adjacent, String name, String colorString ) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.angle = angle;
		this.adjacent = adjacent;
		this.name = name;
		this.triangle = new Triangle(id, x, y, direction, angle, adjacent, name);
		this.colorString = colorString;
		this.triangle.setColor(colorString);
		if(Debug.isDebug3() == true) { System.out.println("Triangle should have color" + colorString);}
	}
	//debug constructor 
	Record() {
		this.id = 0;
		this.x = 55;
		this.y = 55;
		this.direction = 'd';
		this.angle = 55;
		this.adjacent = 55;
		this.name = "bob";
		this.triangle = new Triangle(id, x, y, direction, angle, adjacent, name);
		this.colorString = "#cc9c5e";
		this.triangle.setColor(colorString);
	}	
	//no name or colorString constructor
	Record(int id, double x, double y, char direction, double angle, double adjacent) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.angle = angle;
		this.adjacent = adjacent;
		this.name = namingConvention();
		this.triangle = new Triangle(id, x, y, direction, angle, adjacent, this.name);
	}
	
	private String namingConvention() {
		String start, mid, end;
		start = "<AG>";
		mid = Integer.toString( (int)adjacent ) + direction + Integer.toString( (int)x ) + 'x' + Integer.toString( (int)y );
		end = "!"+ Integer.toString( (int) angle) + "--" + Integer.toString( (int) adjacent );
		return start+mid+end;
	}
	
	public String getColor() {
		return this.colorString;
	}
	public void setColor(String colorString) {
		this.colorString = colorString;
		triangle.setColor(colorString);
	}
	public String fileString() {
		
		String s;
		String d = CONST.DELIM;
		s = Integer.toString(id) + d + Double.toString(x) + d + Double.toString(y) + d +
		direction + d + Double.toString(angle) + d + Double.toString(adjacent) + d + colorString + d + name;
		
		return s;
	}
	
	public Triangle getTriangle() {
		return triangle;
	}
	
	//a debug method (for now) for generating colors for strings
	public String generateSinColorString() {
		int val = (int)Math.abs((Math.sin(angle) * 255.0));
		int val2 = (int)Math.abs((Math.cos(angle) * 255.0));
		int val3 = (int)Math.abs((Math.tan(angle) * 255.0));
		String s = ("rgba("+val+","+val2+","+val3+","+Math.cos(angle)+")");
		return s;
	}
	
	public String toString() {
		return name;
	}
	
	//generate triangle function? Could be in the constructor
}