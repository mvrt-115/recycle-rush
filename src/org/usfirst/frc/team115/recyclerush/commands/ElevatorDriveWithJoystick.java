package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.OI;
import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorDriveWithJoystick extends Command {

	private static final double THRESHOLD = 0.15;

	private int axis;

	public ElevatorDriveWithJoystick(){
		this(OI.AXIS_CONTROL_ELEVATOR);
	}

	public ElevatorDriveWithJoystick(int axis){
		requires(Robot.elevator);
		this.axis = axis;
	}


	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		double axisValue = Robot.oi.getXboxAxis(axis, true); //invert axis, so forward = positive
		if(Math.abs(axisValue) <= THRESHOLD) {
			Robot.elevator.stop();
		} else {
			Robot.elevator.setBrake(false);
			Robot.elevator.controlJoystick(Robot.oi.getXboxAxis(axis, false));
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
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
