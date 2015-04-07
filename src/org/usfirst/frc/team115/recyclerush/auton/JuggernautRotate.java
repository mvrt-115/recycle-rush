package org.usfirst.frc.team115.recyclerush.auton;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class JuggernautRotate extends Command {
	private final double BIN_ROTATE = 1;

	public JuggernautRotate() {
		requires(Robot.roller);
		requires(Robot.intake);
	}

	@Override
	protected void initialize() {
		setTimeout(BIN_ROTATE);
		Robot.intake.close();

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
	protected void end() {
		Robot.roller.stop();
	}
	@Override
	protected void interrupted() {
		end();
	}

}
