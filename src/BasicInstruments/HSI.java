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
	double largeA = 0.0;
	
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
    		dialPt.draw(g);
    		
    		//draw second pointer
    		dialPtL.reposition(cx, cy);
    		dialPtL.setValue(largeA);
    		dialPtL.draw(g);
		}
	}
}
