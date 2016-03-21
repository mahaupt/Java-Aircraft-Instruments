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
        	int doubleSize =  Double.SIZE / Byte.SIZE;
        	//receive data
        	DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        	serverSocket.receive(receivePacket);
        	
        	double altitude = ByteBuffer.wrap(receivePacket.getData(), 0*doubleSize, doubleSize).order(ByteOrder.LITTLE_ENDIAN).getDouble();
        	double altimSet = ByteBuffer.wrap(receivePacket.getData(), 1*doubleSize, doubleSize).order(ByteOrder.LITTLE_ENDIAN).getDouble();
        	alt.setAlt(altitude);
        	alt.setAltSet(altimSet);
        	
        	double heading = ByteBuffer.wrap(receivePacket.getData(), 2*doubleSize, doubleSize).order(ByteOrder.LITTLE_ENDIAN).getDouble();
        	double largeA = ByteBuffer.wrap(receivePacket.getData(), 3*doubleSize, doubleSize).order(ByteOrder.LITTLE_ENDIAN).getDouble();
        	double smallA = ByteBuffer.wrap(receivePacket.getData(), 4*doubleSize, doubleSize).order(ByteOrder.LITTLE_ENDIAN).getDouble();
        	double smallADrift = ByteBuffer.wrap(receivePacket.getData(), 5*doubleSize, doubleSize).order(ByteOrder.LITTLE_ENDIAN).getDouble();
        	hsi.setHeading(heading);
        	hsi.setLargeA(largeA);
        	hsi.setSmallA(smallA);
        	hsi.setSmallADrift(smallADrift);
        	
        	double pitch = ByteBuffer.wrap(receivePacket.getData(), 6*doubleSize, doubleSize).order(ByteOrder.LITTLE_ENDIAN).getDouble();
        	double bank = ByteBuffer.wrap(receivePacket.getData(), 7*doubleSize, doubleSize).order(ByteOrder.LITTLE_ENDIAN).getDouble();
        	double gsAngle = ByteBuffer.wrap(receivePacket.getData(), 8*doubleSize, doubleSize).order(ByteOrder.LITTLE_ENDIAN).getDouble();
        	adi.setPitchBankValues(pitch, bank);
        	adi.setGSAngle(gsAngle);
        	
        	hsi.repaint();
        	alt.repaint();
        	adi.repaint();
        	
        } while(alt.isShowing());
        System.out.println("Breaked");
        serverSocket.close();
    }
}