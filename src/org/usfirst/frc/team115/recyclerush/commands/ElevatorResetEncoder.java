package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class ElevatorResetEncoder extends Command {

	boolean finished;

	public ElevatorResetEncoder(){
		finished = false;
	}

	@Override
	protected void initialize() {
		Robot.oi.rumbleXbox(Joystick.RumbleType.kLeftRumble, .6, 300);
		Robot.oi.rumbleXbox(Joystick.RumbleType.kRightRumble, .6, 300);
	}

	@Override
	protected void execute() {
		Robot.elevator.resetEncoder();
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
