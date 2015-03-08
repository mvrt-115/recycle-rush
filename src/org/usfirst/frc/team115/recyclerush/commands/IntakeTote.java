package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeTote extends Command{

	public IntakeTote(){
		requires(Robot.roller);
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.roller.control(0, -1);
	}

	@Override
	protected boolean isFinished() {
		//run rollers until tote is in all the way
		return Robot.roller.getLeftLimitSwitch();
	}

	@Override
	protected void end() {
		Robot.roller.stop();
	}

	@Override
	protected void interrupted() {}
	
}
