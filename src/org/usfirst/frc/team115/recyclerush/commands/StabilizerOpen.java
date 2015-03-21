package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Stabilizer;

import edu.wpi.first.wpilibj.command.Command;

public class StabilizerOpen extends Command {

	public StabilizerOpen() {
		requires(Robot.stabilizer);
	}

	@Override
	protected void initialize() {
		setTimeout(Stabilizer.TIME_TO_ACTUATE);
	}

	@Override
	protected void execute() {
		Robot.stabilizer.open();
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
}
