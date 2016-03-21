package BasicInstruments;

import java.net.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.swing.*;


@SuppressWarnings("serial")
public class MyFrame extends JFrame
{
	
    
    public static void main(String[] args) throws Exception
    {
    	
    	DatagramSocket serverSocket = new DatagramSocket(9876);
    	byte[] receiveData = new byte[1024];
    	
        Altimeter alt = new Altimeter();
        HSI hsi = new HSI();
        ADI adi = new ADI();
        
        do {
        	//receive data
        	DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        	serverSocket.receive(receivePacket);
        	double altitude = ByteBuffer.wrap(receivePacket.getData()).order(ByteOrder.LITTLE_ENDIAN).getDouble();
        	alt.setAlt((float)altitude);
        	
        	hsi.repaint();
        	alt.repaint();
        	adi.repaint();
        	
        } while(alt.isShowing());
        System.out.println("Breaked");
        serverSocket.close();
    }
}