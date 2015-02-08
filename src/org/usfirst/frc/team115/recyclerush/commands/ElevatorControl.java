package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class ElevatorControl extends Command {
	
	public ElevatorControl () {
		requires(Robot.elevator);
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.elevator.control(Robot.oi.getElevatorAxis());
	}
	
	
	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}

}
