package org.usfirst.frc.team115.recyclerush.subsystems;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import edu.wpi.first.wpilibj.command.Subsystem;

public class LEDStrip extends Subsystem {

	public static final short FUNCTION_SET_COLOR = (short)0;
	public static final short FUNCTION_FLASH = (short)5;
	public static final short FUNCTION_PULSE = (short)1;
	public static final short FUNCTION_PULSE_FADE = (short)4;
	public static final short FUNCTION_BREATHE = (short)3;
	public static final short FUNCTION_RAINBOW = (short)2;

	public static final short STRIP_ADDRESS = (short)0;

	private String ip;
	private int port;

	public LEDStrip(int port, String ip) {
		port = this.port;
		ip = this.ip;
	}

	public void sendLEDCommand(short address, short function, short color1, short color2, short data1, short data2) {
		DatagramSocket clientSocket;
		try {
			clientSocket = new DatagramSocket();
			InetAddress ipaddr = InetAddress.getByName(ip);
			ByteBuffer buffer = ByteBuffer.allocate(12);
			buffer.order(ByteOrder.BIG_ENDIAN);
			buffer.putShort(address);
			buffer.putShort(function);
			buffer.putShort(color1);
			buffer.putShort(color2);
			buffer.putShort(data1);
			buffer.putShort(data2);
			byte[] sendData = buffer.array();
			clientSocket.connect(ipaddr, port);
			clientSocket.send(new DatagramPacket(sendData, sendData.length));
		} catch (UnknownHostException e) {
			System.err.println("Could not get LED IP Address");
			e.printStackTrace();
		} catch (SocketException e) {
			System.err.println("Could not open LED socket");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error sending LED data");
			e.printStackTrace();
		}
	}

	@Override
	protected void initDefaultCommand() {

	}
}
