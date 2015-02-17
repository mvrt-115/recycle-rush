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
	
	Elevator elev;
	
	public ElevatorControl() {
		requires(Robot.elevator);
	}

	@Override
	protected void initialize() {
		elev = Robot.elevator;
		// disable PID
		elev.disable();
		// release the brake
		elev.release();
	}

	@Override
	protected void execute() {
		double axis = Robot.oi.getXboxAxis(OI.AXIS_CONTROL_ELEVATOR);
		if(Math.abs(axis) <= 0.05){
			if(!elev.isBraking())elev.brake();
		}else{
			if(elev.isBraking())elev.release();
			elev.control(Robot.oi.getXboxAxis(OI.AXIS_CONTROL_ELEVATOR));
		}
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		// stop and brake
		elev.control(0);
		elev.brake();
	}

	@Override
	protected void interrupted() {
	    end();
	}

}
