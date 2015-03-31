package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		distance = Math.abs(Robot.elevator.getHeight() - destHeight);
		direction = destHeight < Robot.elevator.getHeight() ? DOWN : UP;
		setTimeout(HEATHER_TIMEOUT);
	}

	@Override
	protected void execute() {
		double speed = direction * PRESET_SPEED * (-1/(1+40000*Math.exp(-0.11 * (100 * Math.abs((distance - Math.abs(Robot.elevator.getHeight() - destHeight)) / distance)))) + 1);
		//		if(Math.abs(distance - (Robot.elevator.getHeight() - destHeight)) / distance <= .75) {
		//			speed = direction * PRESET_SPEED;
		//		} else {
		//			speed = direction * PRESET_SPEED * (1/12) * (100 * Math.abs(distance - (Robot.elevator.getHeight() - destHeight)) / distance) + 0.2;
		//		}
		SmartDashboard.putNumber("Distance", distance);
		SmartDashboard.putNumber("Delta", (distance - (Robot.elevator.getHeight() - destHeight)));
		SmartDashboard.putNumber("Percent", 100 * Math.abs(((distance - Math.abs(Robot.elevator.getHeight() - destHeight))) / distance));
		SmartDashboard.putNumber("Ramped Speed", speed);
		Robot.elevator.setSpeed(speed);
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