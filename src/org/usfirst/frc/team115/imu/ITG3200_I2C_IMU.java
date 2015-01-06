package org.usfirst.frc.team115.imu;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SensorBase;

public class ITG3200_I2C_IMU extends SensorBase {
	I2C i2c;
	private static final byte ADDRESS = 0x68;
	private static final byte SAMPLE_RATE_ADDRESS = 0x15;
	private static final byte SAMPLE_RATE = 0x09;
	private static final byte LOWPASS_FILTER_ADDRESS = 0x16;
	private static final byte LOWPASS_FILTER_98HZ = 0x1a;
	
	public static class Axes {
		public final byte value;
		static final byte X_VAL = 0x00;
		static final byte Y_VAL = 0x02;
		static final byte Z_VAL = 0x04;
		
		public static final Axes X = new Axes(X_VAL);
		public static final Axes Y = new Axes(Y_VAL);
		public static final Axes Z = new Axes(Z_VAL);
		
		private Axes(byte value) {
			this.value = value;
		}
	}
	
	public ITG3200_I2C_IMU(I2C.Port port) {
		i2c = new I2C(port, ADDRESS);
		
		i2c.write(SAMPLE_RATE_ADDRESS, SAMPLE_RATE);
		i2c.write(LOWPASS_FILTER_ADDRESS, LOWPASS_FILTER_98HZ);
	}
}
