package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;



public class DeadTurn extends Command{
	
	private static double goal;
	private static double start;
	
	public DeadTurn() {
		
	}

	@Override
	protected void initialize() {
		start = Robot.drive.getYaw();
		goal = (start + 90)%360;
	}

	@Override
	protected void execute() {
		Robot.drive.drive(0, 1);
	}

	@Override
	protected boolean isFinished() {
		if(Robot.drive.getYaw() > goal || Robot.drive.getYaw() < start)
		{
			return true;
		}
		return false;
	}

	@Override
	protected void end() {
		Robot.drive.stop();
	}

	@Override
	protected void interrupted() {
		end();
		
	}

}
