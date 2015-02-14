package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.OI;
import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.Command;

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
		elev.setMotorControlMode(ControlMode.PercentVbus);
		// release the brake
		elev.release();
	}

	@Override
	protected void execute() {
		elev.control(Robot.oi.getXboxAxis(OI.AXIS_CONTROL_ELEVATOR));
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
	protected void interrupted() {}

}
