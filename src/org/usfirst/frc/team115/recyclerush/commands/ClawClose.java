package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Command;

public class ClawClose extends Command {

	public ClawClose() {
		requires(Robot.claw);
	}

	@Override
	protected void initialize() {
		setTimeout(Claw.TIME_TO_ACTUATE);
	}

	@Override
	protected void execute() {
		Robot.claw.close();
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
