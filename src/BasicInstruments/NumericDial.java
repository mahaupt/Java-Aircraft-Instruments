// Copyright 2016 Marcel Haupt
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http ://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package BasicInstruments;

import java.awt.*;
import java.awt.geom.*;

public class NumericDial {
	double startingAngle = 0; // start angle of the zero
	double spacingAngle = 0; //angle of the space before zero
	
	//for incontinous dials
	boolean incontinousDial;
	
	double maxValue;
	double minValue;
	double bigLineStep;
	
	int digitNumber = 1;
	int cutBeginningDigits = 0;
	boolean leadingZeros = true;
	boolean rotatingDisplay = false;
	int subScaleLines = 1;
	int largeLinesSize = 2;
	int dialDiameter;
	int cx, cy;
	
	Font dispFont;
	Rectangle2D fontRectangle;
	FontMetrics fontMetrics;
	Color numberColor;
	Color dialColor;
	
	NumericDial(int fontsize, int _dialDiameter) {
		dispFont = new Font("Helvertica", Font.BOLD, fontsize);
		dialDiameter = _dialDiameter;
		cx = 150;
		cy = 150;
		
		numberColor = Color.white;
		dialColor = Color.white;
		
		maxValue = 10;
		minValue = 0;
		bigLineStep = 1;
		
		incontinousDial = false;
	}
	
	
	void draw(Graphics g) {
		g.setFont(dispFont);
		
		//draw circle
		g.setColor(dialColor);
		g.drawOval((cx - dialDiameter/2), (cy - dialDiameter/2), dialDiameter, dialDiameter);
		
		
		double fullAngle = 2*Math.PI - spacingAngle;
		
		//draw lines and numbers
		for (double i = minValue; i <= maxValue; i += bigLineStep) {
			if (!incontinousDial && i >= maxValue) break;
			
			double angle = i/maxValue*fullAngle + startingAngle;
				
			//draw text
			double sx = dialDiameter*0.375*Math.sin(angle);
			double sy = -dialDiameter*0.375*Math.cos(angle);
			
			g.setColor(numberColor);
			int dispNumber = (int)(Math.floor(i) / Math.pow(10, cutBeginningDigits));
			String dialText = Integer.toString(dispNumber);
			//leading zeros
			if (leadingZeros) while(dialText.length() < digitNumber) dialText = "0" + dialText;
			
			//calculate text rect
			calcTextRectangle(g, dialText);
			
			Graphics2D g2d = (Graphics2D)g;
			if (rotatingDisplay) {
	    		g2d.rotate(angle, cx, cy);
	    		drawCenteredString(dialText, 0, (int)(-dialDiameter*0.375), g);
	    		g2d.rotate(-angle, cx, cy);
			} else {
				//normal drawing
				drawCenteredString(dialText, (int)(sx), (int)(sy), g);
			}
            
            
            
            //draw large lines
            g.setColor(dialColor);
            
            g2d.rotate(angle, cx, cy);
            g.fillRect(cx-1, cy+(int)(-dialDiameter*0.49), largeLinesSize, (int)(dialDiameter*0.083));
            g2d.rotate(-angle, cx, cy);
            
            
            if (i >= maxValue) break;
            //draw sub lines
            for (int j = 1; j <= subScaleLines; j++) {
            	double x2 = dialDiameter*0.475*Math.sin((i + (double)j * bigLineStep / ((double)subScaleLines+1))/maxValue*fullAngle + startingAngle);
	            double y2 = -dialDiameter*0.475*Math.cos((i + (double)j * bigLineStep / ((double)subScaleLines+1))/maxValue*fullAngle + startingAngle);
	            
            	g.drawLine(cx + (int)x2, cy + (int)y2, cx + (int)(x2*0.9), cy + (int)(y2*0.9));
            }
		}
		
	}
	
	void calcTextRectangle(Graphics g, String testtext) {
		Graphics2D g2d = (Graphics2D) g;
		fontMetrics = g2d.getFontMetrics();
        fontRectangle = fontMetrics.getStringBounds(testtext, g2d);
	}

	//draws a centered string
	void drawCenteredString(String text, int _x, int _y, Graphics g) {
        int x = (cx - (int) fontRectangle.getWidth()/2) + _x;
        int y = (cy - (int) fontRectangle.getHeight()/2) + _y + fontMetrics.getAscent();
        g.drawString(text, x, y);
	}
	
	
	
	public void setNumberColor(Color c) {
		numberColor = c;
	}
	public void setBorderColor(Color c) {
		dialColor = c;
	}
	public void reposition(int _cx, int _cy) {
		cx = _cx;
		cy = _cy;
	}
	public void setFont(Font f) {
		dispFont = f;
	}
	public void setDiameter(int diam) {
		dialDiameter = diam;
	}
	public void setAngles(double startangle, double spaceangle) {
		startingAngle = startangle;
		spacingAngle = spaceangle;
	}
	public void setMinMaxValue(double min, double max, double step) {
		maxValue = max;
		minValue = min;
		bigLineStep = step;
	}
	public void setLines(int lines) {
		subScaleLines = lines;
	}
	public void setDigitNumbers(int _digitNumber, int _cutBeginningDigits, boolean _showZeros) {
		digitNumber = _digitNumber;
		cutBeginningDigits = _cutBeginningDigits;
		leadingZeros = _showZeros;
	}
	public void setRotatingDisplay(boolean _value) {
		rotatingDisplay = _value;
	}
	public void setLargeLineSize( int value ) {
		largeLinesSize = value;
	}
}
