package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Stabilizer;

public class ToggleStabilizer extends Command {

	Stabilizer claw;
	
	public ToggleStabilizer(){
		requires(Robot.stabilizer);
		claw = Robot.stabilizer;
	}

	@Override
	protected void initialize() {
    	claw.open();
	}

	@Override
	protected void execute() {}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		claw.close();
	}

	@Override
	protected void interrupted() {
		end();
	}
	
}
