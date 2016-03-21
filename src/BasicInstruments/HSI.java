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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;

public class HSI extends JFrame {
	private static final long serialVersionUID = 1L;
	private int cx, cy;
	double heading = 0.0;
	double smallA = 0.0;
	double smallADrift = 0.0;
	double largeA = 0.0;
	double miles = 0.0;
	
	public HSI() {
		super("Basic HSI");
		
		//default closing action
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setSize(300, 300 + 20);
        
        //add label and button
        setContentPane(new HSIPanel());
        
        setLocation(300, 0);
        
        //arrange components inside window
        setVisible(true);
        
        cx = this.getWidth()/2;
        cy = this.getHeight()/2;
	}
	
	public void setHeading(double value) {
		heading = value;
	}
	public void setSmallA(double value) {
		smallA = value;
	}
	public void setLargeA(double value) {
		largeA = value;
	}
	public void setSmallADrift(double value) {
		smallADrift = value;
	}
	public void setMiles(double value) {
		miles = value;
	}
	
	
	class HSIPanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		NumericDial compassDial;
		NumericDisplay numDisp;
		DialPointer dialPt;
		DialPointer dialPtL;
		StaticDial statDial;
		
		HSIPanel() {
			super();
			
			compassDial = new NumericDial(16, 260);
			compassDial.setMinMaxValue(0, 36, 3);
			compassDial.setLines(2);
			compassDial.setDigitNumbers(2, 0, false);
			compassDial.setRotatingDisplay(true);
			
			statDial = new StaticDial(260);
			
			numDisp = new NumericDisplay(22);
			numDisp.digitNumber(4);
			//numDisp.setBorderColor(Color.black);
			numDisp.drawBorder(false);
			
			dialPt = new DialPointer(260);
			dialPt.setPointerType(4);
			
			dialPtL = new DialPointer(260);
			dialPtL.setPointerType(3);
		}
		
		
		public void paintComponent(Graphics g) {
    		cx = this.getWidth()/2;
            cy = this.getHeight()/2;
            
            
            //clear background
    		g.setColor(Color.black);
    		g.fillRect(0, 0, this.getWidth(), this.getHeight());
    		
    		//draw number display
    		numDisp.reposition(cx, cy - 50);
    		numDisp.setValue(miles * 10);
    		numDisp.draw(g);
    		
    		//draw comma and line
    		g.setColor(Color.white);
    		g.drawLine(cx-30, cy-36, cx+11, cy-36);
    		g.drawRect(cx + 16, cy - 37, 1, 1);
    		
    		//draw static display
    		statDial.reposition(cx, cy);
    		statDial.draw(g);
    		
    		Graphics2D g2d = (Graphics2D)g;
    		g2d.rotate(heading, cx, cy);
    		
    		//draw compass dial
    		compassDial.reposition(cx, cy);
    		compassDial.draw(g);
    		
    		//draw pointer
    		dialPt.reposition(cx, cy);
    		dialPt.setValue(smallA);
    		dialPt.setoffValue(smallADrift);
    		dialPt.draw(g);
    		
    		//draw second pointer
    		dialPtL.reposition(cx, cy);
    		dialPtL.setValue(largeA);
    		dialPtL.draw(g);
		}
	}
}
