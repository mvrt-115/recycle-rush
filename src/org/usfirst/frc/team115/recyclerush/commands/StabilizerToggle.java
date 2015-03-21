package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Stabilizer;

import edu.wpi.first.wpilibj.command.Command;

public class StabilizerToggle extends Command {

	boolean wasOpen;

	public StabilizerToggle() {
		requires(Robot.stabilizer);
	}

	@Override
	protected void initialize() {
		wasOpen = Robot.stabilizer.isOpen();
		setTimeout(Stabilizer.TIME_TO_ACTUATE);
	}

	@Override
	protected void execute() {
		if(wasOpen) {
			Robot.stabilizer.close();
		} else {
			Robot.stabilizer.open();
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
