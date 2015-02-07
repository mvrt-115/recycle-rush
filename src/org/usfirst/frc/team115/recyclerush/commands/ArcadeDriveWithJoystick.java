package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team115.recyclerush.Robot;

/**
 * Arcade Drive with joystick.
 *
 * @author Lee Mracek
 */

public class ArcadeDriveWithJoystick extends Command {

	public ArcadeDriveWithJoystick() {
	    requires(Robot.drive);
	}
	
	@Override
	protected void initialize() {
	}
	
	@Override
	protected void execute() {
	    Robot.drive.drive(Robot.oi.getJoystick());
	}
	
	@Override
	protected boolean isFinished() {
	    return false;
	}
	
	@Override
	protected void end() {
	    Robot.drive.drive(0, 0);
	}
	
	@Override
	protected void interrupted() {
	    end();
	}
}
