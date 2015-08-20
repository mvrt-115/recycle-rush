package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.lib.Subsystem;
import org.usfirst.frc.team115.robot.Constants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;

public class DriveBase extends Subsystem {
	private CANTalon leftDriveBaseFront = new CANTalon(RobotMap.DRIVE_MOTOR_FRONTLEFT);
	private CANTalon leftDriveBaseRear = new CANTalon(RobotMap.DRIVE_MOTOR_BACKLEFT);

	private CANTalon rightDriveBaseFront = new CANTalon(RobotMap.DRIVE_MOTOR_FRONTRIGHT);
	private CANTalon rightDriveBaseRear = new CANTalon(RobotMap.DRIVE_MOTOR_BACKRIGHT);

	public DriveBase() {
		super("Drivebase");
		leftDriveBaseCenter.changeControlMode(ControlMode.Follower);
		leftDriveBaseCenter.set(leftDriveBaseFront.getDeviceID());
		leftDriveBaseRear.changeControlMode(ControlMode.Follower);
		leftDriveBaseRear.set(leftDriveBaseFront.getDeviceID());

		rightDriveBaseCenter.changeControlMode(ControlMode.Follower);
		rightDriveBaseCenter.set(rightDriveBaseFront.getDeviceID());
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
