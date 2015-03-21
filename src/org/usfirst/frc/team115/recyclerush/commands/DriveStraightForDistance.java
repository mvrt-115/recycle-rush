package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

/**
 * Drives the robot straight
 *
 * @author Akhil Palla
 */
public class DriveStraightForDistance extends DriveStraight {

	private static int THRESHOLD = 100; //100 ticks
	private final double SCALE = (1 / (Math.PI * 8)) * 2 * 1024; //ticks per inch

	private double distance;

	public DriveStraightForDistance(double inches){
		super(false);
		this.distance = inches * SCALE;
	}

	public DriveStraightForDistance(double speed, double inches){
		super(speed, false);
		this.distance = inches * SCALE;
	}

	@Override
	protected void initialize() {
		super.initialize();
		Robot.drive.resetEncoders();
	}

	@Override
	protected boolean isFinished() {
		if(distance > 0) {
			return Robot.drive.getDistance() >= (distance - THRESHOLD);
		}else{
			return Robot.drive.getDistance() <= (distance + THRESHOLD);
		}
	}

}
