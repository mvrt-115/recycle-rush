package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ElevatorUp extends PIDCommand {

	public ElevatorUp(double p, double i, double d) {
		super(p, i, d);
		requires(Robot.elevator);
		SmartDashboard.putString("ElevatorDirection", "Up");
	}

	@Override
	protected double returnPIDInput() {
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.elevator.goUp();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.elevator.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
