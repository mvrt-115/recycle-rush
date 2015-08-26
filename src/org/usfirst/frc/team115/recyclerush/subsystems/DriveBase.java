package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.lib.Subsystem;
import org.usfirst.frc.team115.recyclerush.Constants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;

public class DriveBase extends Subsystem {

	private CANTalon leftDriveBaseFront = new CANTalon(Constants.LEFT_DRIVE_FRONT.getInt());
	private CANTalon leftDriveBaseRear = new CANTalon(Constants.LEFT_DRIVE_REAR.getInt());

	private CANTalon rightDriveBaseFront = new CANTalon(Constants.RIGHT_DRIVE_FRONT.getInt());
	private CANTalon rightDriveBaseRear = new CANTalon(Constants.RIGHT_DRIVE_REAR.getInt());

	public DriveBase() {
		super("Drivebase");
		leftDriveBaseRear.changeControlMode(ControlMode.Follower);
		leftDriveBaseRear.set(leftDriveBaseFront.getDeviceID());

		rightDriveBaseRear.changeControlMode(ControlMode.Follower);
		rightDriveBaseRear.set(rightDriveBaseFront.getDeviceID());
	}

	public void setLeftRightPower(double leftPower, double rightPower) {
		leftDriveBaseFront.set(leftPower);
		rightDriveBaseFront.set(-rightPower);
	}

	@Override
	public void run() {
		super.run();
	}
}
