package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorStop extends Command{
	
	public ElevatorStop(){
		requires(Robot.elevator);
	}
	
	@Override
	protected void initialize() {
		Robot.elevator.stop();
		Robot.elevator.Brake();
	}

	@Override
	protected void execute() {
		Robot.elevator.Release();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}

}
