package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.PIDCommand;

public class DriveStraight extends PIDCommand {
		
	private double displacement;
	
	public DriveStraight(double distance, double p, double i, double d) {
		this(p, i, d);
		Robot.drive.setMode(CANTalon.ControlMode.Position);
		displacement = distance;
	}
	
	public DriveStraight(long ms, double p, double i, double d) {
		this(p, i, d);
		this.setTimeout((float) (ms / 1000));
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
	}

	@Override
	protected void initialize() {
		if (Robot.drive.getMode() == CANTalon.ControlMode.Position) {
			Robot.drive.enableControl();
		}
		this.setSetpoint(Robot.drive.getYaw());
	}
	

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
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
