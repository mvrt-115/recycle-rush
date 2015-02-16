package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Stabilizer;

public class ToggleStabilizer extends Command {

	Stabilizer claw;
	boolean finished = false;
	
	public ToggleStabilizer(){
		requires(Robot.stabilizer);
		claw = Robot.stabilizer;
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
