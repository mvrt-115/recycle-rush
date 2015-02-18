package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team115.recyclerush.Robot;

public class DisableCompressor extends Command {

	public DisableCompressor() {
		requires(Robot.compressor);
	}
	
	@Override
	protected void initialize() {
		Robot.compressor.disable();
	}

	@Override
	protected void execute() {}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {
		end();
	}
}
