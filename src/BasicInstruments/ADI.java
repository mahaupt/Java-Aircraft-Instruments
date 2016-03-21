package BasicInstruments;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

public class ADI extends JFrame {
	private static final long serialVersionUID = 1L;
	private int cx, cy;
	double pitch = 0.0;
	double roll = 0.0;
	
	int sizex = 260;
	int sizey = 260;

	public ADI() {
		super("Basic ADI");
		
		//default closing action
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setSize(300, 300 + 20);
        
        //add label and button
        setContentPane(new ADIPanel());
        
        setLocation(600, 0);
        
        //arrange components inside window
        setVisible(true);
        
        cx = this.getWidth()/2;
        cy = this.getHeight()/2;
	}
	
	public class ADIPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		ArtHorizon artHorizon;
		
		public ADIPanel() {
			super();
			
			artHorizon = new ArtHorizon(sizex, sizey);
			artHorizon.setDisplayGS(true);
		}
		
		
		public void paintComponent(Graphics g) {
			cx = this.getWidth()/2;
            cy = this.getHeight()/2;
            
            
            //clear background
    		g.setColor(Color.black);
    		g.fillRect(0, 0, this.getWidth(), this.getHeight());
    		
    		//draw artifical horizon
    		artHorizon.reposition(cx, cy);
    		artHorizon.draw(g);
    		
    		//draw rect around adi
    		g.drawRect(cx-sizex/2, cy-sizey/2, sizex, sizey);
    		g.setColor(Color.black);
    		g.fillRect(0, 0, this.getWidth(), (this.getHeight()-sizey)/2);
    		g.fillRect(0, this.getHeight()/2+sizey/2, this.getWidth(), (this.getHeight()-sizey)/2);
    		g.fillRect(0, 0, (this.getWidth()-sizex)/2, this.getHeight());
    		g.fillRect(this.getWidth()/2+sizex/2, 0, (this.getWidth()-sizex)/2, this.getHeight());
		}
		
	}
}
