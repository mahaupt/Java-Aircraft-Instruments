package BasicInstruments;

import java.awt.*;
import java.awt.geom.*;

public class NumericDisplay {
	double value;
	boolean displayMinus = false;
	boolean fillZeros = true;
	boolean drawBorder = true;
	int digitNumber = 5;
	int cx, cy;
	
	Font dispFont;
	Rectangle2D fontRectangle;
	FontMetrics fontMetrics;
	Color numberColor;
	Color borderColor;
	
	
	NumericDisplay(int fontsize) {
		cx = 0;
		cy = 0;
		
		dispFont = new Font("Helvertica", Font.BOLD, fontsize);
		
		numberColor = Color.white;
		borderColor = Color.white;
	}
	
	
	public void draw(Graphics g) {
		//calculate borders for text
		g.setFont(dispFont);
		calcTextRectangle(g);
		
		//draw value
		g.setColor(numberColor);
		String text = valueToString(value);
		drawCenteredString(text, 0, 0, g);
		
		//draw border around value
		if (drawBorder) {
			g.setColor(borderColor);
			int spacing = 3;
			g.drawRect(cx - (int)(fontRectangle.getWidth()/2) - spacing - 1, cy - (int)(fontRectangle.getHeight()/2) - spacing, (int)fontRectangle.getWidth() + 2*spacing + 2, (int)fontRectangle.getHeight() + 2*spacing);
		}
	}
	
	
	void calcTextRectangle(Graphics g) {
		String testtext = "";
		for(int i = 0; i < digitNumber; i++) testtext += "0";
		Graphics2D g2d = (Graphics2D) g;
		fontMetrics = g2d.getFontMetrics();
        fontRectangle = fontMetrics.getStringBounds(testtext, g2d);
	}

	// Convert a value to a string
	String valueToString(double _alt) {
		double maxValue = Math.pow(10, digitNumber);
		
		//negative values
		boolean minus = false;
		while (_alt < 0) {
			if (!displayMinus)
				_alt += maxValue;
			minus = true;
		}
		
		//overflow values
		while (_alt > maxValue) _alt -= maxValue;
			
		//parse value to string
		String salt = Integer.toString((int) _alt);
		int missingChars = digitNumber-salt.length();
		
		if (fillZeros)
			for(int i = 0; i < missingChars; i++) salt = "0"+salt;
		else
			for(int i = 0; i < missingChars; i++) salt = " "+salt;
		
		if (displayMinus && minus)
			salt = '-' + salt.substring(1, salt.length()-1);
		
		return salt;
	}
	
	
	//draws a centered string
	void drawCenteredString(String text, int _x, int _y, Graphics g) {
        int x = (cx - (int) fontRectangle.getWidth()/2) + _x;
        int y = (cy - (int) fontRectangle.getHeight()/2) + _y + fontMetrics.getAscent();
        g.drawString(text, x, y);
	}
	
	
	
	//get and set fcts
	public void setValue(double _value) {
		value = _value;
	}
	public double getValue() {
		return value;
	}
	public void displayMinus(boolean b) {
		displayMinus = b;
	}
	public void fillZeros(boolean b) {
		fillZeros = b;
	}
	public void digitNumber(int i) {
		digitNumber = i;
	}
	public void drawBorder(boolean b) {
		drawBorder = b;
	}
	public void setNumberColor(Color c) {
		numberColor = c;
	}
	public void setBorderColor(Color c) {
		borderColor = c;
	}
	public void reposition(int _cx, int _cy) {
		cx = _cx;
		cy = _cy;
	}
	public void setFont(Font f) {
		dispFont = f;
	}
	
}
