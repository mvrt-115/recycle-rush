package org.usfirst.frc.team115.recyclerush;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

public class MecanumDrive extends RobotDrive{
	
	private SpeedController frontLeftMotor;
	private SpeedController frontRightMotor;
	private SpeedController rearLeftMotor;
	private SpeedController rearRightMotor;
	
	public MecanumDrive(SpeedController frontLeft, SpeedController rearLeft, SpeedController frontRight, SpeedController rearRight) {
		super(frontLeft, rearLeft, frontRight, rearRight);
		this.frontLeftMotor = frontLeft;
		this.frontRightMotor = frontRight;
		this.rearLeftMotor = rearLeft;
		this.rearRightMotor = rearRight;
	}

	/**
	 * Drives the robot using mecanumDrive, using a cartesian system (field-centric) and a single 3-axis joystick
	 * @param joystick: The joystick object
	 * @param gyroTheta: The current gyro angle, clockwise from zero
	 */
	public void mecanumCartesian(GenericHID joystick, double gyroTheta){
		double right = joystick.getX();
		double fwd = joystick.getY();
		double clockwise =  RobotMap.JOYSTICK_TWIST_CONSTANT * joystick.getTwist();
		
		double fwdPrime = fwd * Math.cos(gyroTheta) + right * Math.sin(gyroTheta);
		right = right * Math.cos(gyroTheta) - fwd * Math.sin(gyroTheta);
		fwd = fwdPrime;

		setMotors(fwd, right, clockwise);
	}
	
	/**
	 * Drives the robot using mecanumDrive, using a cartesian system (field-centric) and tankd rive
	 * @param leftJoystick: The left joystick object
	 * @param rightJoystick: The right joystick object
	 * @param gyroTheta: The current gyro angle, clockwise from zero
	 */
	public void mecanumCartesianTank(GenericHID rightJoystick, GenericHID leftJoystick, double gyroTheta){
		double fwd = (leftJoystick.getY() + rightJoystick.getY()) / 2;
		double right = rightJoystick.getX();
		double clockwise = RobotMap.JOYSTICK_TWIST_CONSTANT * (leftJoystick.getY() - rightJoystick.getY()) / 2;
		
		double fwdPrime = fwd * Math.cos(gyroTheta) + right * Math.sin(gyroTheta);
		right = right * Math.cos(gyroTheta) - fwd * Math.sin(gyroTheta);
		fwd = fwdPrime;

		setMotors(fwd, right, clockwise);
	}
	
	/**
	 * Drives the robot using mecanum drive, using a polar system (robot-centric) and a single 3-axis joystick
	 * @param joystick: The joystick object
	 */
	public void mecanumPolar(GenericHID joystick){		
		double right = joystick.getX();
		double fwd = joystick.getY();
		double clockwise =  RobotMap.JOYSTICK_TWIST_CONSTANT * joystick.getTwist();
		
		setMotors(fwd, right, clockwise);
	}
	
	/**
	 * Drives the robot using mecanum drive, using a polar system (robot-centric) and Tank Drive
	 * @param leftJoystick: The left joystick object
	 * @para rightJoystick: The right joystick object
	 */
	public void mecanumPolarTank(GenericHID leftJoystick, GenericHID rightJoystick){		
		double fwd = (leftJoystick.getY() + rightJoystick.getY()) / 2;
		double right = rightJoystick.getX();
		double clockwise = RobotMap.JOYSTICK_TWIST_CONSTANT * (leftJoystick.getY() - rightJoystick.getY()) / 2;
		
		setMotors(fwd, right, clockwise);
	}
	
	/**
	 * Sets the motors' powers, given forward/right powers, and spin amount
	 * @param fwd: The forward speed, relative to the robot
	 * @param right: The right speed, relative to the robot
	 * @param clockwise: The spin amount of the robot
	 */
	public void setMotors(double fwd, double right, double clockwise){
		double front_left  = fwd + clockwise + right;
		double front_right = fwd - clockwise - right;
		double rear_left   = fwd + clockwise - right;
		double rear_right  = fwd - clockwise + right;

		//Normalize the values:
		double max = Math.abs(front_left);
		if (Math.abs(front_right)>max) max = Math.abs(front_right);
		if (Math.abs(rear_left)>max)   max = Math.abs(rear_left);
		if (Math.abs(rear_right)>max)  max = Math.abs(rear_right);
		if (max>1){front_left /=max; front_right/=max; rear_left/=max; rear_right/=max;}
		
		frontLeftMotor.set(front_left);
		frontRightMotor.set(front_right);
		rearLeftMotor.set(rear_left);
		rearRightMotor.set(rear_right);
	}

}
