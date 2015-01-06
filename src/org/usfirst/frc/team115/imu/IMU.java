package org.usfirst.frc.team115.imu;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;

public class IMU {
	public IMU() {
		ADXL345_I2C accelerometer = new ADXL345_I2C(I2C.Port.kOnboard, Range.k8G);
		ITG3200_I2C java = new ITG3200_I2C();
	}

}
