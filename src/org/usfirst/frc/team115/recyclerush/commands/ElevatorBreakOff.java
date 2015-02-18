package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorBreakOff extends Command {
	
	private boolean finished = false;
	
	public ElevatorBreakOff() {
		requires(Robot.elevator);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.elevator.release();
		finished = true;
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
