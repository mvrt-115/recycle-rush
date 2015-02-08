package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Closes the arm/roller
 * @author MVRT
 */
public class RollerClose extends Command {

	public RollerClose() {
		requires(Robot.roller);
	}
	
	boolean finished = false;
	
	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.roller.close();
		finished = false;
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
