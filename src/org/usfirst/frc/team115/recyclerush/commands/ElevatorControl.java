package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class ElevatorControl extends Command {

	private Joystick joystick;
	
	public ElevatorControl (Joystick joystick) {
		requires(Robot.elevator);
		this.joystick = joystick;
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Robot.elevator.control(joystick.getY());
	}
	
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
