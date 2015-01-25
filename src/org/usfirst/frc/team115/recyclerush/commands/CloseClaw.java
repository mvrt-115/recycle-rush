package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CloseClaw extends Command {
	
	public CloseClaw() {
		requires(Robot.claw);
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.claw.close();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {}
	
	@Override
	protected void interrupted() {}

}
