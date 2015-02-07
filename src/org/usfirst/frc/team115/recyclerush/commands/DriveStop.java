package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team115.recyclerush.Robot;

public class DriveStop extends Command {

	public DriveStop() {
	    requires(Robot.drive);
	}
	
	@Override
	protected void initialize() {
	    Robot.drive.stop();
	}
	
	@Override
	protected void execute() {
	}
	
	@Override
	protected boolean isFinished() {
	    return true;
	}
	
	@Override
	protected void end() {
	}
	
	@Override
	protected void interrupted() {
	}

}
