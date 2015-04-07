package org.usfirst.frc.team115.recyclerush.indep;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RollerRotate extends Command {
	private double move;
	private double rotate;
	private double time;

	public RollerRotate(double move, double rotate, double time) {
		this.move = move;
		this.rotate = rotate;
		this.time = time;
		requires(Robot.roller);
	}

	@Override
	protected void initialize() {
		this.setTimeout(time);
	}

	@Override
	protected void execute() {
		Robot.roller.control(move, rotate);
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
