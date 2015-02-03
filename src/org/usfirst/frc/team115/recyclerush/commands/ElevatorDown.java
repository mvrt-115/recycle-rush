package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class ElevatorDown extends PIDCommand {

	public ElevatorDown(double p, double i, double d) {
		super(p, i, d);
		requires(Robot.elevator);		
	}

	@Override
	protected double returnPIDInput() {
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {}

	@Override
	protected void initialize() {
		Robot.elevator.release();
	}

	@Override
	protected void execute() {
		Robot.elevator.goDown();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.elevator.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
