package org.usfirst.frc.team115.recyclerush;

import org.usfirst.frc.team115.recyclerush.subsystems.DriveBase;

import edu.wpi.first.wpilibj.DriverStation;
/*
*@author Lee Mracek
*/

public class DriveSystem {
	private DriveBase drive;
	private double oldWheel, quickStopAccumulator, negInertiaAccumulator = 0;
	private double throttleDeadband = 0.02;
	private double wheelDeadband = 0.02;

	public DriveSystem(DriveBase drive) {
		this.drive = drive;
	}

	public void drive(double throttle, double wheel, boolean quickTurn) {
		if (DriverStation.getInstance().isAutonomous())
			return;

		double logScale = 0.6;

		wheel = handleDeadband(wheel, wheelDeadband);
		throttle = handleDeadband(throttle, throttleDeadband);

		double negativeInertia = wheel - oldWheel;

		oldWheel = wheel;

		wheel = Math.sin(Math.PI / 2.0 * logScale * wheel) / Math.sin(Math.PI / 2.0 * logScale);
		wheel = Math.sin(Math.PI / 2.0 * logScale * wheel) / Math.sin(Math.PI / 2.0 * logScale);

		double leftPwm, rightPwm, overPower;
		double sensitivity;

		double angularPower;
		double linearPower;

		// Negative inertia!
		double negInertiaScalar;
		if (wheel * negativeInertia > 0) {
			negInertiaScalar = 2.5;
		} else {
			if (Math.abs(wheel) > 0.65) {
				negInertiaScalar = 5.0;
			} else {
				negInertiaScalar = 3.0;
			}
		}
		sensitivity = Constants.sensitivity.getDouble();

		double negInertiaPower = negativeInertia * negInertiaScalar;
		negInertiaAccumulator += negInertiaPower;

		wheel = wheel + negInertiaAccumulator;
		if (negInertiaAccumulator > 1) {
			negInertiaAccumulator -= 1;
		} else if (negInertiaAccumulator < -1) {
			negInertiaAccumulator += 1;
		} else {
			negInertiaAccumulator = 0;
		}

		linearPower = throttle;

		if (quickTurn) {
			if (Math.abs(linearPower) < 0.2) {
				double alpha = 0.1;
				quickStopAccumulator = (1 - alpha) * quickStopAccumulator + alpha * limit(wheel, 1.0) * 5;
			}
			overPower = 1.0;
			sensitivity = 1.0;
			angularPower = wheel;
		} else {
			overPower = 0.0;
			angularPower = Math.abs(throttle) * wheel * sensitivity - quickStopAccumulator;
			if (quickStopAccumulator > 1) {
				quickStopAccumulator -= 1;
			} else if (quickStopAccumulator < -1) {
				quickStopAccumulator += 1;
			} else {
				quickStopAccumulator = 0.0;
			}
		}

		rightPwm = leftPwm = linearPower;
		leftPwm += angularPower;
		rightPwm -= angularPower;

		if (leftPwm > 1.0) {
			rightPwm -= overPower * (leftPwm - 1.0);
			leftPwm = 1.0;
		} else if (rightPwm > 1.0) {
			leftPwm -= overPower * (rightPwm - 1.0);
			rightPwm = 1.0;
		} else if (leftPwm < -1.0) {
			rightPwm += overPower * (-1.0 - leftPwm);
			leftPwm = -1.0;
		} else if (rightPwm < -1.0) {
			leftPwm += overPower * (-1.0 - rightPwm);
			rightPwm = -1.0;
		}
		drive.setLeftRightPower(leftPwm, rightPwm);
	}

	public double handleDeadband(double val, double deadband) {
		return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
	}

	public static double limit(double v, double limit) {
		return (Math.abs(v) < limit) ? v : limit * (v < 0 ? -1 : 1);
	}
}
