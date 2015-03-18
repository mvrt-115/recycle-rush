package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class ElevatorDriveWithJoystick extends Command {

	private static final double THRESHOLD = 0.15;

	private Joystick joystick;
	private int axis;

	public ElevatorDriveWithJoystick(Joystick joystick, int axis) {
		this.joystick = joystick;
		this.axis = axis;
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		double axisValue = joystick.getRawAxis(this.axis);
		if(Math.abs(axisValue) <= THRESHOLD) {
			Robot.elevator.control(0);
		} else {
			Robot.elevator.control(axisValue);
		}
		Robot.elevator.control(axisValue);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
