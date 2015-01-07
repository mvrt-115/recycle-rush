package org.usfirst.frc.team115.imu;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

public class ADXL345_I2C_IMU implements Accelerometer {
	private I2C i2c;
	public final static byte ADDRESS = 0x53;
	public final static byte DATA_FORMAT_REGISTER = 0x31;
	public static final byte DATA_FORMAT_FULLRES = 0x09;
	public static final byte POWER_CTL_ADDRESS = 0x2D;
	public static final byte POWER_CTL_AUTOMEASURE = 0x08;
	public static final byte DATA_REGISTER = 0x32;
	public static final double G_PER_LSB = 0.00390625;
	
	public static class Axes {
		public final byte value;
		static final byte X_VAL = 0x00;
		static final byte Y_VAL = 0x01;
		static final byte Z_VAL = 0x02;
		
		public static final Axes X = new Axes(X_VAL);
		public static final Axes Y = new Axes(Y_VAL);
		public static final Axes Z = new Axes(Z_VAL);
		
		public Axes(byte value) {
			this.value = value;
		}
	}
	
	public ADXL345_I2C_IMU(I2C.Port port, Range range) {
		i2c = new I2C(port, ADDRESS);
		
		setRange(range);
		
		i2c.write(POWER_CTL_ADDRESS, POWER_CTL_AUTOMEASURE);
	}
	
	public void setRange(Range range) {
		byte val = 0;
		
		switch(range) {
		case k2G: 
			val = 0;
			break;
		case k4G:
			val = 1;
			break;
		case k8G:
			val = 2;
			break;
		case k16G:
			val = 3;
			break;
		}
		i2c.write(DATA_FORMAT_REGISTER, DATA_FORMAT_FULLRES | val);
	}
	
	public double getX() {
		return getAcceleration(Axes.X);
	}
	
	public double getY() {
		return getAcceleration(Axes.Y);
	}
	
	public double getZ() {
		return getAcceleration(Axes.Z);
	}
	
	public double getAcceleration(Axes axis) {
		byte[] raw = new byte[2];
		i2c.read(axis.value, raw.length, raw);
		
		return (short) ((raw[1] << 8) | raw[0]) * G_PER_LSB;
	}
}
