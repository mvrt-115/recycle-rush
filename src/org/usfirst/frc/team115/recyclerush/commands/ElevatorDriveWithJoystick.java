package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.OI;
import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class ElevatorDriveWithJoystick extends Command {

	private static final double THRESHOLD = 0.15;

	private Joystick joystick;
	private int axis;

	public ElevatorDriveWithJoystick(){
		this(OI.AXIS_CONTROL_ELEVATOR);
	}

	public ElevatorDriveWithJoystick(int axis){
		this(Robot.oi.getDriveJoystick(), axis);
	}

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
			Robot.elevator.control(axisValue * Elevator.MAX_SPEED_FINE);
		}
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
