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
	private double distance;
	private static final double STOP_THRESHOLD = 0.5;
	private static double PRESET_SPEED = 1;
	private int direction = 0;
	private boolean shouldRamp = true;

	public ElevatorToHeight(double destHeight) {
		requires(Robot.elevator);
		this.destHeight = destHeight;
	}

	public ElevatorToHeight(double destHeight, boolean ramp) {
		this(destHeight);
		this.shouldRamp = ramp;
	}

	public void setDest(double dest){
		destHeight = dest;
	}

	@Override
	protected void initialize() {
		Robot.elevator.setBrake(false);
		distance = Math.abs(Robot.elevator.getHeight() - destHeight);
		direction = destHeight < Robot.elevator.getHeight() ? DOWN : UP;
		setTimeout(HEATHER_TIMEOUT);
	}

	@Override
	protected void execute() {
		double x = (100 * Math.abs((distance - Math.abs(Robot.elevator.getHeight() - destHeight)) / distance));
		double ramp = shouldRamp ? getRamp(x) : 1;

		Robot.elevator.setSpeed(direction * PRESET_SPEED * ramp);
		/*DEBUG*/SmartDashboard.putNumber("ElevatorSpeed", direction*PRESET_SPEED*ramp);
		//SmartDashboard.putNumber("Ramp", ramp);
	}

	public double getRamp(double percentToTarget) {
		return ((1 - 0.70 / (1 + 12000*Math.exp(-0.115 * percentToTarget))));
	}

	@Override
	protected boolean isFinished() {
		if(direction == UP){
			return (Robot.elevator.getHeight() >= destHeight);
		}
		else{
			return (Robot.elevator.getHeight() <= destHeight);
		}
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