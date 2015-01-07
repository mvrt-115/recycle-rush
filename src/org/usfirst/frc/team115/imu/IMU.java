package org.usfirst.frc.team115.imu;

import org.usfirst.frc.team115.imu.ITG3200_I2C_IMU.Filter;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;

public class IMU extends SensorBase implements LiveWindowSendable {
	public static final Range DEFAULT_RANGE = Range.k8G;
	public static final Filter DEFAULT_FILTER = Filter.k98Hz;
	
	private ADXL345_I2C_IMU accel;
	private ITG3200_I2C_IMU gyro;

	public IMU(I2C.Port port) {
		this(port, DEFAULT_RANGE);
	}

	public IMU(I2C.Port port, Range range) {
		this(port, DEFAULT_FILTER, range);
	}
	
	public IMU(I2C.Port port, Filter filter) {
		this(port, filter, DEFAULT_RANGE);
	}
	
	public IMU(I2C.Port port, Filter filter, Range range) {
		accel = new ADXL345_I2C_IMU(port, range);
		gyro = new ITG3200_I2C_IMU(port, 100, filter);
	}
	
	public double getXRate() {
		return gyro.getX();
	}
	
	public double getYRate() {
		return gyro.getY();
	}
	
	public double getZRate() {
		return gyro.getZ();
	}
	
	public double getXAcceleration() {
		return accel.getX();
	}
	
	public double getYAcceleration() {
		return accel.getY();
	}
	
	public double getZAcceleration() {
		return accel.getZ();
	}

	private ITable table;
	
	@Override
	public void initTable(ITable subtable) {
		table = subtable;
	}

	@Override
	public ITable getTable() {
		return table;
	}

	@Override
	public String getSmartDashboardType() {
		return("6DegreeIMU");
	}

	@Override
	public void updateTable() {
		table.putNumber("X Accel", getXAcceleration());
		table.putNumber("Y Accel", getYAcceleration());
		table.putNumber("Z Accel", getZAcceleration());
		
		table.putNumber("X Rate", getXRate());
		table.putNumber("Y Rate", getYRate());
		table.putNumber("Z Rate", getZRate());
	}

	@Override
	public void startLiveWindowMode() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopLiveWindowMode() {
		// TODO Auto-generated method stub
		
	}

}
