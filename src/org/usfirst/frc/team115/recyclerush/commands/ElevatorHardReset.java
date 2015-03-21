package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorHardReset extends Command {

	private static final double SPEED_DOWN = -0.8;

	public ElevatorHardReset(){
		requires(Robot.elevator);
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.elevator.control(SPEED_DOWN);
	}

	@Override
	protected boolean isFinished() {
		return Robot.elevator.isLimitPressed();
	}

	@Override
	protected void end() {
		Robot.elevator.resetEncoder();
		Robot.elevator.stop();
	}

	@Override
	protected void interrupted() {
		Robot.elevator.stop();
	}

}
