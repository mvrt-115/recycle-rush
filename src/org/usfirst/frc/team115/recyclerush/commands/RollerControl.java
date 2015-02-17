package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.OI;
import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Controls the rollers using joystick axis values
 * @author MVRT
 */
public class RollerControl extends Command {

	public RollerControl() {
		requires(Robot.roller);
	}
	
	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.roller.control(Robot.oi.getXboxAxis(OI.ROLLERCONTROL_AXIS_X), 
			Robot.oi.getXboxAxis(OI.ROLLERCONTROL_AXIS_Y));
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}

}
