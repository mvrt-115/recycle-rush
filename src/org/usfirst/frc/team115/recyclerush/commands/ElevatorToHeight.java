package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves the elevator to a certain preset
 * @author Akhil Palla, Marcus Plutowski
 */
public class ElevatorToHeight extends Command {

	private double destHeight;
	private static final double RAMP_THRESHOLD = 4;
	private static final double STOP_THRESHOLD = 1;
	private double Speed = 0;
	//private static double PRESET_SPEED = Math.min(1, Robot.elevator.PRESET_SPEED + 0.15); For later
	private static double PRESET_SPEED = 0.65; //ElevatorControl makes - up and + down; we want the opposite for this -_-*-
	public ElevatorToHeight(double destHeight) {
		requires(Robot.elevator);
		this.destHeight = destHeight;
	}

	public void setDest(double dest){
		destHeight = dest;
	}

	@Override
	protected void initialize() {
		Robot.elevator.setBrake(false);
	}

	@Override
	protected void execute() {
		if(Math.abs(Robot.elevator.getHeight()) - RAMP_THRESHOLD < Math.abs(RAMP_THRESHOLD))
		{
			Speed = 0.7*Math.abs(Robot.elevator.getHeight() - destHeight)/RAMP_THRESHOLD + 0.3;
		}
		else
		{
			Speed = PRESET_SPEED;
		}
		if(destHeight < Robot.elevator.getHeight()) {
			Robot.elevator.setSpeed(Speed);
		} else {
			Robot.elevator.setSpeed(-1 * Speed);
		}
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(Robot.elevator.getHeight() - destHeight) <= STOP_THRESHOLD;
	}

	@Override
	protected void end() {
		Robot.elevator.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}