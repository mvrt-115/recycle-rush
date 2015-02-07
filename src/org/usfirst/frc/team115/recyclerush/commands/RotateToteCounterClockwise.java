package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Rotates the tote counter-clockwise until it is ready
 * to intake
 * @author MVRT
 */
public class RotateToteCounterClockwise extends Command {

	public RotateToteCounterClockwise() {
		requires(Robot.roller);
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.roller.control(-1.0, 0.0);
	}

	@Override
	protected boolean isFinished() {
		return Robot.roller.getLeftLimitSwitch() && Robot.roller.getRightLimitSwitch();
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
