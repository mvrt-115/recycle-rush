package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class OpenGrabber extends Command {
	
	public OpenGrabber() {
		requires(Robot.grabber);
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.grabber.open();
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
