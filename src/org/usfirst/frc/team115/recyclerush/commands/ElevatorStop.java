package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorStop extends Command{
	
	public ElevatorStop(){
		requires(Robot.elevator);
	}
	
	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.elevator.stop();	
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}

}
