package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Command;

public class ClawOpen extends Command {

	public ClawOpen() {
		requires(Robot.claw);
	}

	@Override
	protected void initialize() {
		setTimeout(Claw.TIME_TO_ACTUATE);
	}

	@Override
	protected void execute() {
		Robot.claw.open();
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
