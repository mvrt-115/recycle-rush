package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.OI;
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
		double axis = joystick.getRawAxis(OI.AXIS_CONTROL_ELEVATOR);
		if(Math.abs(axis) <= THRESHOLD) {
			Robot.elevator.control(0);
		} else {
			Robot.elevator.control(axis);
		}
		Robot.elevator.control(axis);
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
