package org.usfirst.frc.team115.recyclerush.commands.indep;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class WaitForHeightCommand extends Command {

	double height;

	public WaitForHeightCommand(double height) {
		this.height = height;
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {}

	@Override
	protected boolean isFinished() {
		return Robot.elevator.getHeight() <= height;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
}
