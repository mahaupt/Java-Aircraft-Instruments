package BasicInstruments;

import java.awt.*;

public class StaticDial {
	int cx, cy;
	int diameter;
	Color dialColor;
	
	public StaticDial(int dialDiam) {
		dialColor = Color.white;
		diameter = dialDiam;
	}
	
	
	public void draw(Graphics g) {
		Polygon p = new Polygon();
		p.addPoint(cx, cy - diameter/2);
		p.addPoint(cx - 2, cy - 8 - diameter/2);
		p.addPoint(cx + 2, cy - 8 - diameter/2);
		
		g.setColor(dialColor);
		Graphics2D g2d = (Graphics2D)g;
		
		//small triangles
		for (int i = 0; i < 8; i++) {
			g2d.rotate(Math.PI/4, cx, cy);
			g.fillPolygon(p);
		}
		
		
		//large triangle
		
		Polygon p2 = new Polygon();
		p2.addPoint(cx, cy - diameter/2);
		p2.addPoint(cx - 8, cy - 8 - diameter/2);
		p2.addPoint(cx + 8, cy - 8 - diameter/2);
		g.fillPolygon(p2);
	}
	
	
	public void reposition(int _cx, int _cy) {
		cx = _cx;
		cy = _cy;
	}
}
