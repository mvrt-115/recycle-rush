package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves the elevator to a certain preset
 * @author Akhil Palla, Marcus Plutowski
 */
public class ElevatorToHeight extends Command {

	private static final int UP = 1;
	private static final int DOWN = -1;

	private static double HEATHER_TIMEOUT = 8;

	private double destHeight;
	private double halfHeight;
	private double distance;
	private static final double STOP_THRESHOLD = 0.7;
	private static double PRESET_SPEED = 1;
	private int direction = 0;
	private boolean shouldRamp = true;
	private boolean shouldStop = true;
	public ElevatorToHeight(double destHeight) {
		requires(Robot.elevator);
		this.destHeight = destHeight;
	}

	public ElevatorToHeight(double destHeight, boolean ramp) {
		this(destHeight);
		this.shouldRamp = ramp;
	}

	public ElevatorToHeight(double destHeight, boolean ramp, boolean stop) {
		this(destHeight, ramp);
		this.shouldStop = stop;
	}

	public void setDest(double dest){
		destHeight = dest;
	}

	@Override
	protected void initialize() {
		Robot.elevator.setBrake(false);
		halfHeight = (destHeight + Robot.elevator.getHeight()) / 2;
		distance = Math.abs(Robot.elevator.getHeight() - destHeight);
		direction = destHeight < Robot.elevator.getHeight() ? DOWN : UP;
		setTimeout(HEATHER_TIMEOUT);
	}

	@Override
	protected void execute() {
		double x = (100 * Math.abs((distance - Math.abs(Robot.elevator.getHeight() - destHeight)) / distance));
		double ramp = shouldRamp ? getRamp(x) : 1;
		Robot.elevator.setSpeed(direction * PRESET_SPEED * ramp);
	}

	public double getRamp(double percent) {
		return (-1/(1+40000*Math.exp(-0.11 * percent)) + 1);
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(Robot.elevator.getHeight() - destHeight) <= STOP_THRESHOLD || isTimedOut();
	}

	@Override
	protected void end() {
		if(shouldStop) {
			Robot.elevator.stop();
		}
	}

	@Override
	protected void interrupted() {
		end();
	}
}