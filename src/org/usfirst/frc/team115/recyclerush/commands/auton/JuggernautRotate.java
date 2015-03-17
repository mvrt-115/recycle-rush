package org.usfirst.frc.team115.recyclerush.commands.auton;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.commands.DelayCommand;
import org.usfirst.frc.team115.recyclerush.commands.RollerOpen;

import edu.wpi.first.wpilibj.command.Command;

public class JuggernautRotate extends Command{

	private final double BIN_ROTATE = 1.2;
	
	public JuggernautRotate()
	{
		requires(Robot.roller);
	}
	@Override
	protected void initialize() {
		setTimeout(BIN_ROTATE);
		Robot.roller.close();
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
