package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Stabilizer;

public class ToggleStabilizer extends Command {
	
	public ToggleStabilizer(){
		requires(Robot.stabilizer);
	}

	@Override
	protected void initialize() {
    	Robot.stabilizer.open();
	}

	@Override
	protected void execute() {}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.stabilizer.close();
	}

	@Override
	protected void interrupted() {
		end();
	}
	
}
