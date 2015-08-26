package com.usfirst.frc.team115.lib.trajectory;
import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SampleVelocity extends Command {

	double dt; //ms
	double dx; //Inches
	double elapsedT;
	double elapsedX;
	double initial;
	double instVel;
	double avgVel;
	double maxVel;

	private static int ENCODER_SCALE = 1444;
	private static double ERROR = 1;
	private final double SCALE = (1 / (Math.PI * 8)) * 2 * ENCODER_SCALE / ERROR; //ticks per inch

	public SampleVelocity(double time) {
		setTimeout(7);
		this.initial = Timer.getFPGATimestamp();

	}

	@Override
	protected void initialize() {
		Robot.drive.resetEncoders();
		this.dt = 20;
		this.dx = 0;
	}

	@Override
	protected void execute() {
		this.dt = Timer.getFPGATimestamp() - this.dt;
		this.dx = Robot.drive.getDistance()/SCALE - this.dx;
		this.elapsedT = Timer.getFPGATimestamp() - this.initial;
		this.elapsedX = Robot.drive.getDistance()/SCALE;
		this.instVel = dx/dt;
		this.avgVel = elapsedX/elapsedT;
		this.maxVel = maxVel > instVel ? instVel : maxVel;
		this.log();
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void end() {
	}

	protected void log() {
		SmartDashboard.putNumber("Velocity", instVel);
		SmartDashboard.putNumber("Avg Velocity", avgVel);
		SmartDashboard.putNumber("Max Velocity", maxVel);
	}
	@Override
	protected void interrupted() {
	}
}
