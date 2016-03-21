package BasicInstruments;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class Altimeter extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static double alt = 0.0;
	static double alt_set = 1013;
	private int inst_size = 260;
	private int cx, cy;
	
    public Altimeter() {
        //create Window
        super("Basic Altimeter");
        
        //default closing action
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setSize(300, 300 + 20);
        
        //add label and button
        setContentPane(new AltimeterPanel());
        
        setLocation(0, 0);
        
        //arrange components inside window
        //frame.pack();
        setVisible(true);
        
        cx = this.getWidth()/2;
        cy = this.getHeight()/2;
    }
    
    
    void setAlt(double _alt) {
    	alt = _alt;
    }
    

	class AltimeterPanel extends JPanel {
    	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		NumericDisplay altDispl;
    	NumericDisplay altSetDispl;
    	NumericDial altDial;
    	DialPointer dialPt;
    	
    	AltimeterPanel() {
    		super();
    		altDispl = new NumericDisplay(20);
    		altSetDispl = new NumericDisplay(12);
    		altSetDispl.digitNumber(4);
    		
    		altDial = new NumericDial(18, 260);
    		altDial.setLargeLineSize(2);
    		dialPt = new DialPointer(260);
    	}
    	
    	public void paintComponent(Graphics g) {
    		cx = this.getWidth()/2;
            cy = this.getHeight()/2;
            
    		
    		//clear background
    		g.setColor(Color.black);
    		g.fillRect(0, 0, this.getWidth(), this.getHeight());
    		
    		//draw dial
    		altDial.reposition(cx, cy);
    		altDial.draw(g);
    		
    		//draw altitude value and box
    		altDispl.reposition(cx, cy - 50);
    		altDispl.setValue(alt);
    		altDispl.draw(g);
    		
    		//draw altimeter setting box
    		altSetDispl.reposition(cx, cy + 65);
    		altSetDispl.setValue(alt_set);
    		altSetDispl.draw(g);
            
            //draw ALT text
    		g.setFont(new Font("Helvertica", Font.BOLD, 18));
            drawCenteredString("ALT", 0, (int)(+inst_size*0.1), g);
            
            //draw mbar text
            g.setFont(new Font("Helvertica", Font.BOLD, 10));
            drawCenteredString("mbar", (int)0, 80, g);
    		
    		//draw pointers
            double th_alt = alt / 10000;
            dialPt.reposition(cx, cy);
            dialPt.setValue(th_alt);
            dialPt.draw(g);
    	}
    	
    	//draw a centered string
    	void drawCenteredString(String text, int _x, int _y, Graphics g) {
    		Graphics2D g2d = (Graphics2D) g;
            FontMetrics fm = g2d.getFontMetrics();
            
            Rectangle2D r = fm.getStringBounds(text, g2d);
            int x = (cx - (int) r.getWidth()/2) + _x;
            int y = (cy - (int) r.getHeight()/2) + _y + fm.getAscent();
            g.drawString(text, x, y);
    	}
    	
    }
}
