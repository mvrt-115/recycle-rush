package org.usfirst.frc.team115.recyclerush.commands.auton;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class JuggernautRotate extends Command{

	private final int BIN_ROTATE = 3;
	
	public JuggernautRotate()
	{
		requires(Robot.roller);
	}
	@Override
	protected void initialize() {
		setTimeout(BIN_ROTATE);
	}

	@Override
	protected void execute() {
		Robot.roller.control(-1.0, 0.0);	
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void interrupted() {
		end();
	}
	
	@Override
	protected void end() {
		Robot.roller.stop();
	}

}
