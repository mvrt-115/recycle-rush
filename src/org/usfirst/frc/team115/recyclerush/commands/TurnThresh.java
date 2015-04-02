package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;

/*
 * Turns w/o PID, using a constant speed
 * @author Marcus Plutowski
 */
public class TurnThresh extends PIDCommand{
	public static double Speed = 0;
	public static double Target = 0;
	public static final double DEFAULT_SPEED = 0.5;
	public TurnThresh(double speed, double TurnAmount) {
		super(0.1, 0, 0); //0.1 so that I can see which direction the PID wants to move in
		setTarget(TurnAmount);
		setSpeed(speed);
	}
	public TurnThresh(double TurnAmount) {
		super(0.1, 0, 0);
		setTarget(TurnAmount);
		setSpeed(DEFAULT_SPEED);
	}
	public TurnThresh(boolean Left){ //Turn 90 right or left
		if(Left == true){
			TurnTresh(DEFAULT_SPEED, 90);
		}
		else{
			TurnThresh(DEFAULT_SPEED, -90);
		}
	}
	private void setSpeed(double newSpeed){
		this.Speed = newSpeed;
	}
	private void setTarget(double newTarget){
		this.Target = newTarget;
	}


	@Override
	protected void end() {
		Robot.drive.stop();
	}

	@Override
	protected void execute() {
		//Empty; all is handled in usePIDOutput()
	}

	@Override
	protected void initialize() {
		Robot.drive.stop();
		setInputRange(-180, 180);
		getPIDController().setAbsoluteTolerance(3);
		getPIDController().setContinuous(true);
		double dest = Math.abs((Robot.navx.getYaw() + Target + 180) % 360) - 180;
		setSetpoint(dest);
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected double returnPIDInput() {
		return Robot.navx.getYaw();
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.drive.control(0, Speed*Math.signum(output));
	}

	@Override
	protected boolean isFinished() {
		return getPIDController().onTarget(); //Not using finished_past and such because this Threshold
	}
}
