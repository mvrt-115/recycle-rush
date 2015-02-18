package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ResetElevatorEncoder extends Command{

	boolean finished = false;
	
	public ResetElevatorEncoder() {
		requires(Robot.elevator);
	}
	
	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.elevator.resetEncoder();
		finished = true;
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}

}
