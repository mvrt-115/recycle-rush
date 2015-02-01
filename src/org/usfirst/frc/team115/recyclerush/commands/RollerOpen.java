package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RollerOpen extends Command {

	public RollerOpen() {
		requires(Robot.leftRoller);
		requires(Robot.rightRoller);
	}
	
	@Override
	protected void initialize() {
		Robot.leftRoller.open();
		Robot.rightRoller.open();
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
	protected void interrupted() {}

}
