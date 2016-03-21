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