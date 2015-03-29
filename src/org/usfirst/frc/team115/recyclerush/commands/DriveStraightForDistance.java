package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

/**
 * Drives the robot straight
 *
 * @author Akhil Palla
 */
public class DriveStraightForDistance extends DriveStraight {

	private static int THRESHOLD = 300; //100 ticks
	private static int RAMP_THRESHOLD = 600; //400 ticks
	private final double SCALE = (1 / (Math.PI * 8)) * 2 * 1444; //ticks per inch

	private double distance;

	public DriveStraightForDistance(double inches){
		this(DriveStraight.SPEED_DEFAULT, inches);
	}

	public DriveStraightForDistance(double speed, double inches){
		super((inches < 0)?-speed:speed, false);
		this.distance = inches * SCALE;
	}

	@Override
	protected void initialize() {
		super.initialize();
		Robot.drive.resetEncoders();
	}

	@Override
	public double getSpeed(){
		double scaler = 1;
		//if distance remaining is less than the ramping threshold distance
		if(Math.abs(distanceLeft()) < RAMP_THRESHOLD){
			scaler = 0.2 + 0.8 * Math.abs(distanceLeft())/RAMP_THRESHOLD;
		}
		return super.getSpeed() * scaler;
	}

	private double distanceLeft(){
		return distance - Robot.drive.getDistance();
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(distanceLeft()) <= THRESHOLD;
	}

}
