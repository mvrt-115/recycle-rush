package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.PIDCommand;

public class DriveStraight extends PIDCommand {
	
	public static final double DEFAULT_SPEED = 0.5;

	private double desiredAngle;
	private double distance;
	private double speed;
	private boolean hasTimeLimit, hasDistanceLimit, hasSpeed;
	private Encoder encoder;
	private Gyro gyro;
	private long startTime, timeLimit;

	public DriveStraight(double distance, double p, double i, double d) {
		this(distance, DEFAULT_SPEED, p, i, d);
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
	
	public DriveStraight(double p, double i, double d) {
		super(p, i, d);
		hasDistanceLimit = hasSpeed = hasTimeLimit = false;
		gyro = new Gyro(0);
		this.setSetpoint(desiredAngle);
	}

	@Override
	protected double returnPIDInput() {
		return gyro.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		if (hasSpeed)
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
