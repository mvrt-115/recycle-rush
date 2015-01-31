package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.PIDCommand;

public class DriveStraight extends PIDCommand {
	
	public DriveStraight(double p, double i, double d) {
		super(p, i, d);
		Robot.drive.setMode(CANTalon.ControlMode.PercentVbus);
	}

	@Override
	protected double returnPIDInput() {
		return Robot.drive.getYaw();
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.drive.drive(Robot.oi.getJoystick().getY(), output);
	}

	@Override
	protected void initialize() {
		//Robot.drive.getCurrentCommand().cancel();
	}
	

	@Override
	protected void execute() {
		System.out.println("No homo");
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
