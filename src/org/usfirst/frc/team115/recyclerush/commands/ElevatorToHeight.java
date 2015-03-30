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
	private static final double STOP_THRESHOLD = 1.5;
	private static double PRESET_SPEED = 1;
	private int direction = 0;
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
		halfHeight = (destHeight + Robot.elevator.getHeight()) / 2;
		direction = destHeight < Robot.elevator.getHeight() ? DOWN : UP;
		setTimeout(HEATHER_TIMEOUT);
	}

	@Override
	protected void execute() {
		Robot.elevator.setSpeed(direction * PRESET_SPEED);
		if(direction == DOWN) {
			if(Robot.elevator.getHeight() < halfHeight && !Robot.elevator.isRamping()) {
				Robot.elevator.setVoltageRampRate(48);
			}
		} else if(direction == UP) {
			if(Robot.elevator.getHeight() > halfHeight && !Robot.elevator.isRamping()) {
				Robot.elevator.setVoltageRampRate(48);
			}
		}
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(Robot.elevator.getHeight() - destHeight) <= STOP_THRESHOLD || isTimedOut();
	}

	@Override
	protected void end() {
		Robot.elevator.setVoltageRampRate(0);
		Robot.elevator.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}