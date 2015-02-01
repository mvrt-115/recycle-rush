package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.PIDCommand;

public class DriveStraight extends PIDCommand {
		
	private double displacement;
	private long stopTime = -1;
	
	public DriveStraight(double distance, double p, double i, double d) {
		this(p, i, d);
		Robot.drive.setMode(CANTalon.ControlMode.Position);
		displacement = distance;
	}
	
	public DriveStraight(long ms, double p, double i, double d) {
		this(p, i, d);
		stopTime = System.currentTimeMillis() + ms;
	}
	
	public DriveStraight(double p, double i, double d) {
		super(p, i, d);
		requires(Robot.drive);
		Robot.drive.setMode(CANTalon.ControlMode.PercentVbus);
	}

	@Override
	protected double returnPIDInput() {
		return Robot.drive.getYaw();
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.drive.drive((Robot.drive.getMode() == CANTalon.ControlMode.Position) ? displacement : Robot.oi.getJoystick().getY(), output);
		System.out.println("use PID Output");
	}

	@Override
	protected void initialize() {
		this.setSetpoint(Robot.drive.getYaw());
	}
	

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		if (stopTime > 0) {
			return System.currentTimeMillis() >= stopTime;
		}
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
