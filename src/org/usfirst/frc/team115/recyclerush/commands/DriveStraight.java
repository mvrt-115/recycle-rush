package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveStraight extends PIDCommand {
	
	public static final double DEFAULT_SPEED = 0.5;
	
	public static final int DISTANCE = 0;
	public static final int TIME = 1;
	public static final int JOYSTICKCONTROL = 2; //Sorry crappy name
	
	private double desiredAngle; 
	private double distance;
	private double speed;
	private boolean hasTimeLimit, hasDistanceLimit, useJoystick;
	private Encoder encoder;
	private Gyro gyro;
	private long startTime, timeLimit;

	/*Old Constructors:
	 * public DriveStraight(double inDistance, double p, double i, double d) {
		this(inDistance, DEFAULT_SPEED, p, i, d);
		distance = inDistance;
	}
	
	public DriveStraight(double distance, double speed, double p, double i, double d) {
		this(p, i, d);
		hasDistanceLimit = hasSpeed = true;
		this.distance = distance;
		this.speed = speed;
		encoder = new Encoder(RobotMap.DRIVE_STRAIGHT_ENCODER_A, RobotMap.DRIVE_STRAIGHT_ENCODER_B);
	}
	
	public DriveStraight(long ms, double p, double i, double d) {
		this(p, i, d);
		hasTimeLimit = hasSpeed = true;
		timeLimit = ms;
		speed = DEFAULT_SPEED;
		encoder = new Encoder(RobotMap.DRIVE_STRAIGHT_ENCODER_A, RobotMap.DRIVE_STRAIGHT_ENCODER_B);
		startTime = System.currentTimeMillis();
	}
	*/
	
	
	/*Modes
	 *  0: User-set distance
	 *  1: User-set time
	 *  2: Use joystick
	*/
	public DriveStraight(double p, double i, double d, int mode) {
		super(p, i, d);
		hasDistanceLimit = hasTimeLimit = false;
		useJoystick = false;
		gyro = new Gyro(0);
		this.setSetpoint(desiredAngle);
		speed = (long)SmartDashboard.getNumber("Drive speed", (double)DEFAULT_SPEED);
		encoder = new Encoder(RobotMap.DRIVE_STRAIGHT_ENCODER_A, RobotMap.DRIVE_STRAIGHT_ENCODER_B);
		
		switch(mode) {
			case DISTANCE: //Drive a set distance
				hasDistanceLimit = true;
				distance = SmartDashboard.getNumber("Drive distance");
				break;
			case TIME: //Drive for a period of time
				hasTimeLimit = true;
				timeLimit = (long)SmartDashboard.getNumber("Drive time");
				startTime = System.currentTimeMillis();
				break;
			case JOYSTICKCONTROL: //Use joystick with PID
				useJoystick = true;
		}
	}

	@Override
	protected double returnPIDInput() {
		return gyro.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		if (!useJoystick)
			Robot.drive.drive(speed, output);
		else
			Robot.drive.drive(Robot.oi.getJoystick().getY(), output);
	}

	@Override
	protected void initialize() {
		desiredAngle = returnPIDInput();
	}

	@Override
	protected boolean isFinished() {
		if(hasDistanceLimit)
			return encoder.getDistance() >= distance;
		else if (hasTimeLimit)
			return (System.currentTimeMillis()-startTime) >= timeLimit;
		else
			return false;
	}

	@Override
	protected void end() {
		Robot.drive.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected void execute() {

	}
}
