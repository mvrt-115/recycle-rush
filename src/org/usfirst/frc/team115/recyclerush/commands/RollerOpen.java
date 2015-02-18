package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Opens the roller/intake arm
 * @author MVRT
 */
public class RollerOpen extends Command {

	boolean finished = false;
	
	public RollerOpen() {
		requires(Robot.roller);
	}
	
	@Override
	protected void initialize() {
    	System.out.println("Opening roller");
	}

	@Override
	protected void execute() {
		Robot.roller.open();
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