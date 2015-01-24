package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class OpenClaw extends Command {
	
	public OpenClaw(){}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.claw.open();
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
