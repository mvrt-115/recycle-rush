package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.PIDCommand;

public class DriveStraight extends PIDCommand {

	double desiredAngle;
	Gyro gyro;

	public DriveStraight(double p, double i, double d) {
		super(p, i, d);

		gyro = new Gyro(0);
		this.setSetpoint(desiredAngle);
	}

	@Override
	protected double returnPIDInput() {
		return gyro.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.drive.drive(Robot.oi.getJoystick().getY(), output);
	}

	@Override
	protected void initialize() {
		desiredAngle = returnPIDInput();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.drive.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected void execute() {

	}
}
