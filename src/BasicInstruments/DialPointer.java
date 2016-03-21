package BasicInstruments;

import java.awt.*;

public class DialPointer {
	int cx, cy;
	double value;
	double offvalue;
	int pointerDiameter;
	int pointerType = 1;
	
	Color pointerColor;
	Color tipColor;
	
	DialPointer(int diameter) {
		pointerDiameter = diameter;
		
		cx = 150;
		cy = 150;
		
		pointerColor = Color.white;
		tipColor = Color.orange;
	}
	
	
	public void draw(Graphics g) {
		switch(pointerType) {
		case 2:
			drawArrowPointer(g);
			break;
		case 3:
			drawLargeArrowPointer(g);
			break;
		case 4:
			drawVORVertPointer(g);
			break;
		default:
			drawSimplePointer(g);
		}
	}
	
	
	private void drawArrowPointer(Graphics g) {
		double angle = value * 2*Math.PI;
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.rotate(angle, cx, cy);
		
		Polygon p = new Polygon();
		p.addPoint(cx, cy - (int)(pointerDiameter*0.4));
		p.addPoint(cx - 4, cy + 12 - (int)(pointerDiameter*0.4));
		p.addPoint(cx + 4, cy + 12 - (int)(pointerDiameter*0.4));
		
		g.setColor(Color.black);
		g.drawPolygon(p);
		g.setColor(pointerColor);
		g.fillPolygon(p);
		g.fillRect(cx-1, cy - (int)(pointerDiameter*0.38), 2, (int)(pointerDiameter * 0.78));
		
		g2d.rotate(-angle, cx, cy);
	}
	
	
	private void drawLargeArrowPointer(Graphics g) {
		double angle = value * 2*Math.PI;
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.rotate(angle, cx, cy);
		
		Polygon p = new Polygon();
		p.addPoint(cx-(int)(pointerDiameter*0.035), cy - (int)(pointerDiameter * 0.34));
		p.addPoint(cx-(int)(pointerDiameter*0.06), cy - (int)(pointerDiameter * 0.34));
		p.addPoint(cx, cy - (int)(pointerDiameter*0.41));
		p.addPoint(cx+(int)(pointerDiameter*0.06), cy - (int)(pointerDiameter * 0.34));
		p.addPoint(cx+(int)(pointerDiameter*0.035), cy - (int)(pointerDiameter * 0.34));
		
		g.setColor(pointerColor);
		g.drawPolygon(p);
		g.drawLine(cx, cy + (int)(pointerDiameter*0.4), cx, cy + (int)(pointerDiameter * 0.34));
		
		g.drawLine(cx - (int)(pointerDiameter*0.035), cy + (int)(pointerDiameter*0.34), cx - (int)(pointerDiameter*0.035), cy - (int)(pointerDiameter * 0.34));
		g.drawLine(cx + (int)(pointerDiameter*0.035), cy + (int)(pointerDiameter*0.34), cx + (int)(pointerDiameter*0.035), cy - (int)(pointerDiameter * 0.34));
		
		g2d.rotate(-angle, cx, cy);
	}
	
	
	private void drawSimplePointer(Graphics g) {
		double angle = value * 2*Math.PI;
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.rotate(angle, cx, cy);
		
		g.setColor(pointerColor);
		g.fillRect(cx-1, cy - (int)(pointerDiameter*0.48), 2, (int)(pointerDiameter*0.48));
		g.setColor(tipColor);
		g.fillRect(cx-1, cy - (int)(pointerDiameter*0.48), 2,  (int)(pointerDiameter*0.1));
		
		g2d.rotate(-angle, cx, cy);
	}
	
	
	private void drawVORVertPointer(Graphics g) {
		double angle = value * 2*Math.PI;
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.rotate(angle, cx, cy);
		
		Polygon p = new Polygon();
		p.addPoint(cx, cy - (int)(pointerDiameter*0.4));
		p.addPoint(cx - 4, cy + 12 - (int)(pointerDiameter*0.4));
		p.addPoint(cx + 4, cy + 12 - (int)(pointerDiameter*0.4));
		
		g.setColor(Color.black);
		g.drawPolygon(p);
		g.setColor(pointerColor);
		g.fillPolygon(p);
		
		//draw dev circles
		g.drawOval(cx-3 - (int)(pointerDiameter*0.12), cy-3, 6, 6);
		g.drawOval(cx-3 - (int)(pointerDiameter*0.24), cy-3, 6, 6);
		g.drawOval(cx-3 + (int)(pointerDiameter*0.12), cy-3, 6, 6);
		g.drawOval(cx-3 + (int)(pointerDiameter*0.24), cy-3, 6, 6);
		
		//first rect
		g.setColor(Color.orange);
		g.fillRect(cx-1, cy - (int)(pointerDiameter*0.05), 2, (int)(pointerDiameter * 0.1));
		g.setColor(pointerColor);
		g.fillRect(cx-1, cy - (int)(pointerDiameter*0.38), 2, (int)(pointerDiameter * 0.24));
		
		int offpix = (int)(Math.toDegrees(offvalue)* 2 * (pointerDiameter*0.12));
		//dev rect
		g.fillRect(cx-1 - offpix, cy - (int)(pointerDiameter*0.12), 2, (int)(pointerDiameter * 0.26));
		
		//last rect
		g.fillRect(cx-1, cy + (int)(pointerDiameter*0.16), 2, (int)(pointerDiameter * 0.24));
		
		g2d.rotate(-angle, cx, cy);
	}
	
	
	public void setValue(double _value) {
		// from zero to one
		value = _value;
		value -= Math.floor(value); 
	}
	public void setoffCalue(double _value) {
		offvalue = _value;
	}
	public void reposition(int _cx, int _cy) {
		cx = _cx;
		cy = _cy;
	}
	public void setPointerType(int value) {
		pointerType = value;
	}
}
