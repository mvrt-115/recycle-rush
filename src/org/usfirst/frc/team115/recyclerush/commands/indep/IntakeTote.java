package org.usfirst.frc.team115.recyclerush.commands.indep;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeTote extends Command {

	private static final double DEFAULT_TIMEOUT = 1.0;

	private double timeout;

	public IntakeTote(){
		this(DEFAULT_TIMEOUT);
	}

	public IntakeTote(double timeout){
		requires(Robot.intake);
		requires(Robot.roller);
		this.timeout = timeout;
	}

	@Override
	protected void initialize() {
		setTimeout(timeout);
	}

	@Override
	protected void execute() {
		Robot.intake.close();
		Robot.roller.control(-1, 0);
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