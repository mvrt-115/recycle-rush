package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 * Drives the robot straight
 *
 * @author Akhil Palla
 */
public class DriveStraight extends PIDCommand {

	public static final double SPEED_DEFAULT = 0.5;
	public static final double P = 0.008;
	public static final double I = 0;
	public static final double D = 0;

	private double speed;
	private boolean joystick;

	public DriveStraight(boolean joystick){
		this(SPEED_DEFAULT, joystick);
	}

	public DriveStraight(double speed, boolean joystick){
		super(P, I, D);
		requires(Robot.drive);
		this.speed = speed;
		this.joystick = joystick;
	}

	@Override
	protected void initialize() {
		Robot.drive.stop();
		setInputRange(-180, 180);
		getPIDController().setOutputRange(-0.4, 0.4);
		getPIDController().setContinuous(true);
		setSetpoint(Robot.navx.getYaw());
	}

	@Override
	protected void execute() {
		//do nothing here: Everything is handled in usePIDOutput
	}

	@Override
	protected double returnPIDInput() {
		return Robot.navx.getYaw();
	}

	@Override
	protected void usePIDOutput(double output) {
		if(joystick){
			Robot.drive.controlJoystickMove(Robot.oi.getDriveJoystick(), output);
		}else{
			Robot.drive.control(speed, output);
		}
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
