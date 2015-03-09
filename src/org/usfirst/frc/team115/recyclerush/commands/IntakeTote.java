package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeTote extends Command{

	public IntakeTote(){
		requires(Robot.roller);
	}

	@Override
	protected void initialize() {
	       setTimeout(1.5);
	}

	@Override
	protected void execute() {
	    Robot.roller.close();
		Robot.roller.control(0, 1);
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void end() {
		Robot.roller.stop();
	}

	@Override
	protected void interrupted() {}
	
}
