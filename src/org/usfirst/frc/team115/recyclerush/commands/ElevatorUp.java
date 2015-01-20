package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
//import org.usfirst.frc.team115.robot.exceptions.MotorSpeedException;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorUp extends Command {

	public ElevatorUp() {
		requires(Robot.Elevator);
	}
	

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.Elevator.GoUp();
		if (Robot.Elevator.isUpSwitchSet() == true) {
			end();
		}
		
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.Elevator.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
