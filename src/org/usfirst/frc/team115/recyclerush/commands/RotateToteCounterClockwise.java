package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Rotates the tote counter-clockwise until it is ready
 * to intake
 * @author MVRT
 */
public class RotateToteCounterClockwise extends Command {

	boolean finished = false;
	
	public RotateToteCounterClockwise() {
		requires(Robot.roller);
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.roller.control(-1.0, 0.0);
		finished = true;
	}

	@Override
	protected boolean isFinished() {
		return finished;
		//return Robot.roller.getIntakeLimitSwitchLeft() && Robot.roller.getIntakeLimitSwitchRight();
	}

	@Override
	protected void end() {
		Robot.roller.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
