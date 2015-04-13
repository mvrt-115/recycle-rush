package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Command;

public class ClawToggle extends Command {

	private boolean wasOpen;

	public ClawToggle() {
		requires(Robot.claw);
	}

	@Override
	protected void initialize() {
		wasOpen = Robot.claw.isOpen();
		setTimeout(Claw.TIME_TO_ACTUATE);
	}

	@Override
	protected void execute() {
		if(wasOpen){
			Robot.claw.close();
		}else{
			Robot.claw.open();
		}
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
}