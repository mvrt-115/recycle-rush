package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ResetEncoders extends Command {
	
	boolean finished = false;

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		Robot.drive.resetAll();
		finished = true;
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
