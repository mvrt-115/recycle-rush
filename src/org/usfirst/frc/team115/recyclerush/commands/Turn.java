package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 * Turns the robot to a desired angle
 * @author Akhil Palla
 */
public class Turn extends PIDCommand {

	public static final double SPEED_DEFAULT = 0.4;
	public static final double P = 0.008;
	public static final double I = 0;
	public static final double D = 0;

	private double delta;
	private boolean finished_past = false;

	public Turn(double delta) {
		super(P, I, D);
		requires(Robot.drive);
		this.delta = delta;
	}

	@Override
	protected void initialize() {
		Robot.drive.stop();
		setInputRange(0, 360);
		getPIDController().setOutputRange(-0.6, 0.6);
		getPIDController().setContinuous(true);
		double dest = (Robot.navx.getYaw() + delta + 360)%360;
		setSetpoint(dest);
	}

	@Override
	protected void execute() {
		//do nothing here: Everything is handled in usePIDOutput
	}

	@Override
	protected double returnPIDInput() {
		return (Robot.navx.getYaw() + 360)%360;
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.drive.control(0, output);
	}

	@Override
	protected boolean isFinished() {
		boolean past = finished_past;
		finished_past = getPIDController().onTarget();
		return past && finished_past;
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
