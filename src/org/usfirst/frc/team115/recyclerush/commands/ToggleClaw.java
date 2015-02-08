package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleClaw extends Command {

	Claw claw;
	boolean finished = false;
	
	public ToggleClaw(){
		requires(Robot.claw);
		claw = Robot.claw;
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		if(claw.isOpen())claw.close();
		else claw.open();
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
