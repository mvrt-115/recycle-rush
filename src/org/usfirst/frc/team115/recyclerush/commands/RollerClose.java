package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Closes the arm/roller
 * @author MVRT
 */
public class RollerClose extends Command {

	private boolean finished = false;

	public RollerClose() {
		requires(Robot.roller);
	}
	
	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.roller.close();
		finished = true;
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}

}
