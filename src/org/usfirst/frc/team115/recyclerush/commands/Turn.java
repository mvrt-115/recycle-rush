package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.PIDCommand;

public class Turn extends PIDCommand {

	private static final double K = 90;
	double desiredAngle;
	Gyro gyro;
	

	public Turn(double p, double i, double d) {
		super(p, i, d);

		gyro = new Gyro(0);
	}

	@Override
	protected double returnPIDInput() {
		return gyro.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.drive.drive(Robot.oi.getJoystick().getX(), output);
	}

	@Override
	protected void initialize() {}
	
	@Override
	protected void execute() {
		desiredAngle = returnPIDInput() + (Robot.oi.getJoystick().getX()*K);
		this.setSetpoint(desiredAngle);

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

}
