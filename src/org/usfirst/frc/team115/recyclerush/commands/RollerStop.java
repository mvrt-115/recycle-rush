package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Stops the motors of the Robot's roller
 * @author MVRT
 */
public class RollerStop extends Command {

	boolean finished = false;
	
	public RollerStop() {
		requires(Robot.roller);
	}
	
	@Override
	protected void initialize() {}
	
	@Override
	protected void execute() {
		Robot.roller.stop();
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
