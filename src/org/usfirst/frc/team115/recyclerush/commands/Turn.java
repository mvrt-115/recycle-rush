package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.PIDCommand;

public class Turn extends PIDCommand {

	private double desiredAngle; // the final angle we strive for
	private double goal;

	public Turn(double goal) {
		super(0, 0, 0);

		this.goal = goal;
	}

	/**
	 * @return the gyro's current angle
	 */
	@Override
	protected double returnPIDInput() {
		return Robot.drive.getYaw();
	}

	/**
	 * Drives the robot forward at the joystick's speed, and at the output angle.
	 * @param output angle to turn
	 */
	@Override
	protected void usePIDOutput(double output) {
		Robot.drive.drive(Robot.oi.getJoystick().getY(),  output);
	}

	@Override
	protected void initialize() {
		desiredAngle = goal + Robot.drive.getYaw(); // Goal degree.
	}

	@Override
	protected void execute() {
	}

	/**
	 * @returns whether the current angle is within 2 degrees of desired angle
	 */
	@Override
	protected boolean isFinished() {
		if(Math.abs(desiredAngle - Robot.drive.getYaw()) <= 2){
			return true;
		}
		return false;
	}

	/**
	 * Stops the robot
	 */
	@Override
	protected void end() {
		Robot.drive.stop();
	}

	/**
	 * Calls end()
	 */
	@Override
	protected void interrupted() {
		end();
	}

}
