package org.usfirst.frc.team115.lib.trajectory;
import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SampleVelocity extends Command {

	double dt = 1; //ms

	double elapsedT = 0;
	double elapsedX = 0;

	double initialTime = 0;
	double instVel = 0;
	double instAcc = 0;
	double avgVel = 0;
	double avgAcc = 0;
	double maxVel = 0;
	double maxAcc = 0;
	double pastVel = 0;

	private static int ENCODER_SCALE = 1444;
	private static double ERROR = 1;
	private final double SCALE = (1 / (Math.PI * 8)) * 2 * ENCODER_SCALE / ERROR; //ticks per inch

	public SampleVelocity() {
		setTimeout(7);

		this.initialTime = Timer.getFPGATimestamp();

	}

	@Override
	protected void initialize() {
		this.initialTime = Timer.getFPGATimestamp();
		instVel = 0;
		instAcc = 0;

		Robot.drive.resetEncoders();
		this.dt = 0.01;
	}

	@Override
	protected void execute() {
		this.dt = Timer.getFPGATimestamp() - this.elapsedT; //This is the unupdated elapsed time from the last loop
		this.elapsedT = Timer.getFPGATimestamp() - this.initialTime;
		this.elapsedX = Robot.drive.getDistance()/SCALE;

		this.instAcc = Math.sqrt(Math.pow(Robot.navx.getWorldLinearAccelX()*9.8, 2) + Math.pow(Robot.navx.getWorldLinearAccelY()*9.8, 2));
		this.instVel = 10*Robot.drive.getVelocity()/SCALE; //We get ticks per 100 milliseconds; we want inches/sec
		//this.avgVel = elapsedX/elapsedT;


		//this.avgAcc = (this.avgAcc*(elapsedT-dt) + instAcc*dt)/elapsedT;

		this.maxAcc = maxAcc < instAcc ? instAcc : maxAcc;
		this.maxVel = maxVel < instVel ? instVel : maxVel;

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
		SmartDashboard.putNumber("Max Velocity", maxVel);

		//SmartDashboard.putNumber("Acceleration", instAcc);
		SmartDashboard.putNumber("Acceleration", maxAcc);

		SmartDashboard.putNumber("ElapsedX", elapsedX);
		SmartDashboard.putNumber("ElapsedT", elapsedT);
	}
	@Override
	protected void interrupted() {
	}
}
