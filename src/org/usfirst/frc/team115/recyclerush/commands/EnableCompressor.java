package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team115.recyclerush.Robot;

public class EnableCompressor extends Command {

	public EnableCompressor() {
		requires(Robot.compressor);
	}
	
	@Override
	protected void initialize() {
		Robot.compressor.enable();
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
