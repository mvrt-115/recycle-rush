package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveStraight extends PIDCommand {
		
	private double displacement;
	//private double distance;
	
	//distance = SmartDashboard.getNumber("drive distance");
	private boolean timeUsed = false;
	
	public DriveStraight(double distance, double p, double i, double d) {
		this(p, i, d);
		timeUsed = false;
		Robot.drive.setMode(CANTalon.ControlMode.Position);
		SmartDashboard.putString("DriveStraight Mode", "Distance");
		SmartDashboard.putString("Distance", distance+"");
		displacement = distance;
		SmartDashboard.putNumber("DriveDistance", displacement);
		SmartDashboard.putBoolean("TimeUsed", timeUsed);
	}
	
	public DriveStraight(long ms, double p, double i, double d) {
		this(p, i, d);
		SmartDashboard.putString("DriveStraight Mode", "Time");
		SmartDashboard.putString("Drive time", ms+"");
		this.setTimeout((float) (ms / 1000));
		timeUsed = true;
	}
	
	public DriveStraight(double p, double i, double d) {
		super(p, i, d);
		timeUsed = false;
		requires(Robot.drive);
		Robot.drive.setMode(CANTalon.ControlMode.PercentVbus);
		SmartDashboard.putString("DriveStraight Mode", "Default");
	}

	@Override
	protected double returnPIDInput() {
		return Robot.drive.getYaw();
	}

	@Override
	protected void usePIDOutput(double output) {
		SmartDashboard.putNumber("Chassis Gyro Angle Yaw", returnPIDInput());
		if(timeUsed) {
			Robot.drive.drive(1, output);
		} else {
			Robot.drive.drive((Robot.drive.getMode() == CANTalon.ControlMode.Position) ? displacement : Robot.oi.getJoystick().getY(), output);
		}
	}

	@Override
	protected void initialize() {
		if (Robot.drive.getMode() == CANTalon.ControlMode.Position) {
			Robot.drive.enableControl();
		}
		this.setSetpoint(Robot.drive.getYaw());
		SmartDashboard.putNumber("Chassis Gyro Angle Yaw", returnPIDInput());
	}
	

	@Override
	protected void execute() { }

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
