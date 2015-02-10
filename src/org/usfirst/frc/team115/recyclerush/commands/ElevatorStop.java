package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ElevatorStop extends Command{
	
	boolean finished = false;
	
	public ElevatorStop(){
		requires(Robot.elevator);
		SmartDashboard.putString("ElevatorDirection", "Stop");
	}
	
	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.elevator.stop();
		Robot.elevator.brake();
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
