package org.usfirst.frc.team115.recyclerush.commands;
import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * Drives for a distance w/o any PID
 * @author (this.works())? "Marcus Plutowski and Akhil Palla :D":"Lee Mracek"
 */
public class DriveDistance extends Command {

	private final double SCALE = 12 * (1 / (Math.PI * 8)) * 2 * 1024;
	private final double SPEED_DEFAULT = 0.7;

	/** The desired distance, in encoder ticks */
	private double goal = 0;

	/**
	 * Drives for a certain distance
	 * @param target: The distance, in inches
	 */
	public DriveDistance(double target) {
		requires(Robot.drive);
		goal = target * SCALE;
	}

	@Override
	protected void initialize() {
		Robot.drive.zeroEncoders();
	}

	@Override
	protected void execute() {
		if(goal > 0) //we want to go forwards
			Robot.drive.drive(SPEED_DEFAULT, 0);
		else if(goal < 0) //we want to go backwards
			Robot.drive.drive(-SPEED_DEFAULT, 0);
		SmartDashboard.putNumber("Goal", goal);
	}

	@Override
	protected boolean isFinished() {
		if(goal < 0) {
			return Robot.drive.getDistance() <= goal;
		} else {
			return Robot.drive.getDistance() >= goal;
		}
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
