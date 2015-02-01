package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RollerTurn extends Command {

	public RollerTurn() { 
		requires(Robot.leftRoller);
		requires(Robot.rightRoller);
	}
	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		double motorSpeed = Robot.oi.getJoystick().getX();
		
		Robot.leftRoller.set(motorSpeed);
		Robot.rightRoller.set(-motorSpeed);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.leftRoller.stop();
		Robot.rightRoller.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
