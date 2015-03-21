package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorReleaseBrake extends Command {

	boolean finished;

	public ElevatorReleaseBrake(){
		requires(Robot.elevator);
		finished = false;
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.elevator.setBrake(false);
		finished = true;
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}

}
