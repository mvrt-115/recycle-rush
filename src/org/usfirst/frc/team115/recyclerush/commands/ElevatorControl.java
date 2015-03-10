package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.OI;
import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Controls the elevator using joystick input
 * @author MVRT
 */
public class ElevatorControl extends Command {
	
	public ElevatorControl() {
		requires(Robot.elevator);
	}

	@Override
	protected void initialize() {
		// release the brake
		Robot.elevator.unBrake();
	}

	@Override
	protected void execute() {
		double axis = Robot.oi.getXboxAxis(OI.AXIS_CONTROL_ELEVATOR);
		if(Math.abs(axis) <= 0.15){
			Robot.elevator.control(0);
			Robot.elevator.brake();
		} else {
			Robot.elevator.unBrake();
			Robot.elevator.control(Robot.oi.getXboxAxis(OI.AXIS_CONTROL_ELEVATOR));
		}
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		// stop and brake
		Robot.elevator.control(0);
		Robot.elevator.brake();
	}

	@Override
	protected void interrupted() {
	    end();
	}

}
