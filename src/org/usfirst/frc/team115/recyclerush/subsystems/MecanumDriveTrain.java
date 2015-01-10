package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.imu.IMU;
import org.usfirst.frc.team115.recyclerush.MecanumDrive;
import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.RobotMap;
import org.usfirst.frc.team115.recyclerush.commands.MecanumDriveWithJoystick;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

public class MecanumDriveTrain extends Subsystem{
	
	private MecanumDrive drive;
	
	private IMU gyro = new IMU(I2C.Port.kOnboard);
	
	private CANTalon frontLeft = new CANTalon(RobotMap.FRONT_LEFT);
	private CANTalon rearLeft = new CANTalon(RobotMap.REAR_LEFT);
	private CANTalon frontRight = new CANTalon(RobotMap.FRONT_RIGHT);
	private CANTalon rearRight = new CANTalon(RobotMap.REAR_RIGHT);
	
	public MecanumDriveTrain() {
		super();
		drive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
	}
	
	@Override
	protected void initDefaultCommand() { 
		setDefaultCommand(new MecanumDriveWithJoystick());
	}
	
	/**
	 * Stops the robot
	 */
	public void stop(){
		drive.setLeftRightMotorOutputs(0, 0);
	}
	
	/**
	 * Drives the robot
	 * @param joystick The joystick to drive based on
	 */
	public void drive(Joystick joystick) {
		double gyroTheta = gyro.getYaw();
		drive.mecanumCartesian(Robot.oi.getJoystick(), gyroTheta);
	}
	
}
