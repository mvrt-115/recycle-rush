package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RollerStop extends Command {

	public RollerStop() {
		requires(Robot.leftRoller);
		requires(Robot.rightRoller);
	}
	
	@Override
	protected void initialize() {
		Robot.leftRoller.stop(); 
		Robot.rightRoller.stop();
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
