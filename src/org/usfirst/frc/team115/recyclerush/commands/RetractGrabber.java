package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RetractGrabber extends Command {

	private boolean finished = false;
	
	public RetractGrabber() {
		requires(Robot.binGrabber);
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.binGrabber.retract();
		finished = false;
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {
		end();
	}

}
