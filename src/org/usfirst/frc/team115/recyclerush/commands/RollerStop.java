package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Stops the motors of the Robot's roller
 * @author MVRT
 *
 */

public class RollerStop extends Command {

	public RollerStop() {
		requires(Robot.roller);
	}
	
	@Override
	protected void initialize() {
		Robot.roller.stop();
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