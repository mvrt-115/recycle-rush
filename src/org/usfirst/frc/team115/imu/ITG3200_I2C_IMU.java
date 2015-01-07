package org.usfirst.frc.team115.imu;

import edu.wpi.first.wpilibj.I2C;

public class ITG3200_I2C_IMU {
	I2C i2c;
	private static final byte ADDRESS = 0x68;
	private static final byte SAMPLE_RATE_ADDRESS = 0x15;
	private static final byte LOWPASS_FILTER_ADDRESS = 0x16;
	private static final byte FS_SEL = 0x18;
	private static final byte DATA_REGISTER = 0x1D;
	private static final double LSB_PER_DEGREESECOND = 14.375;
	private Filter FILTER = Filter.k98Hz;
	
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
	/**
	 * 
	 * @param port
	 * @param rate Rate in Hz
	 * @param filter
	 */
	public ITG3200_I2C_IMU(I2C.Port port, int rate, Filter filter) {
		i2c = new I2C(port, ADDRESS);
		
		setSampleRate(rate);
		setFilter(filter);
		
	}
	
	public void setSampleRate(int rate) {
	    int temp = FILTER == Filter.k256Hz ? 8000/rate - 1 : 1000/rate - 1;
	    if(temp > 255)
	    	temp = 255;
	    if(temp < 0) {
	    	temp = 0;
	    }
		i2c.write(SAMPLE_RATE_ADDRESS, temp);
	}
	
	public void setFilter(Filter filter) {
		byte val = 2;
		switch(filter) {
		case k256Hz:
			val = 0;
			break;
		case k188Hz:
			val = 1;
			break;
		case k98Hz:
			val = 2;
			break;
		case k42Hz:
			val = 3;
			break;
		case k20Hz:
			val = 4;
			break;
		case k10Hz:
			val = 5;
			break;
		case k5Hz:
			val = 6;
			break;
		}
		i2c.write(LOWPASS_FILTER_ADDRESS, FS_SEL | val);
	}
	
	public double getX() {
		return getRate(Axes.X);
	}
	
	public double getY() {
		return getRate(Axes.Y);
	}
	
	public double getZ() {
		return getRate(Axes.Z);
	}
	
	public double getRate(Axes axis) {
		byte[] raw = new byte[2];
		i2c.read(DATA_REGISTER + axis.value, raw.length, raw);
		
		return (short) ((raw[0] << 8 ) | raw[1]) / LSB_PER_DEGREESECOND;
	}
	public enum Filter {
		k256Hz,
		k188Hz,
		k98Hz,
		k42Hz,
		k20Hz,
		k10Hz,
		k5Hz
		
	}
}
