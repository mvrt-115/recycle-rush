package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.PIDCommand;

public class DriveStraight extends PIDCommand {
	
	private double desiredAngle;
	private double desiredDistance;
	private double desiredSpeed;
	private long stopTime;
	
	private boolean useTimeLimit;
		
	private Gyro gyro;
	
	//public static final double DEFAULT_SPEED = 0.5;

	public DriveStraight(double distance, double p, double i, double d, byte uselessValue) {
		this(p, i, d);
		Robot.drive.setMode(CANTalon.ControlMode.Position);
		desiredDistance = distance;
	}
	
	public DriveStraight(double speed, double p, double i, double d) {
		this(p, i, d);
		Robot.drive.setMode(CANTalon.ControlMode.Speed);
		desiredSpeed = speed;
	}
	
	public DriveStraight(long ms, double p, double i, double d) {
		this(p, i, d);
		stopTime = System.currentTimeMillis() + ms;
		useTimeLimit = true;
	}
	
	public DriveStraight(double p, double i, double d) {
		super(p, i, d);
		useTimeLimit = false;
		gyro = new Gyro(0);
		Robot.drive.setMode(CANTalon.ControlMode.Speed);
		Robot.drive.enableControl();
		this.setSetpoint(desiredAngle);
	}

	@Override
	protected double returnPIDInput() {
		return gyro.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		if (Robot.drive.getMode() == CANTalon.ControlMode.Speed) {
			Robot.drive.drive(desiredSpeed, output);
		}
		else {
			Robot.drive.drive(Robot.oi.getJoystick().getY(), output);
		}
	}

	@Override
	protected void initialize() {
		desiredAngle = returnPIDInput();
	}
	

	@Override
	protected void execute() {
		usePIDOutput(desiredAngle);
	}

	@Override
	protected boolean isFinished() {
		if (useTimeLimit) {
			return System.currentTimeMillis() >= stopTime;
		}
		else if (Robot.drive.getMode() == CANTalon.ControlMode.Position) {
			return Robot.drive.getDistance() >= desiredDistance;
		}
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

}
