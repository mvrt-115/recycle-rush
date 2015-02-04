package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ElevatorStop extends Command{
	
	public ElevatorStop(){
		requires(Robot.elevator);
		SmartDashboard.putString("ElevatorDirection", "Stop");
	}
	
	@Override
	protected void initialize() {
		Robot.elevator.stop();
		Robot.elevator.brake();
	}

	@Override
	protected void execute() {}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}

}
