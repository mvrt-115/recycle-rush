package org.usfirst.frc.team115.recyclerush.auton;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;

/*
 * Turns w/o PID, using a constant speed
 * @author Marcus Plutowski
 */
public class TurnThresh extends PIDCommand{
	private double speed = 0;
	private double target = 0;
	private static final double kP = 0.1;
	private static final double kI = 0.0;
	private static final double kD = 0.0;
	public static final double DEFAULT_SPEED = 0.5;
	private static final double THRESHOLD = 3;
	public TurnThresh(double speed, double turnAmount) {
		super(kP, kI, kD); //0.1 so that I can see which direction the PID wants to move in
		setTarget(turnAmount);
		setSpeed(speed);
	}
	public TurnThresh(double turnAmount) {
		this(DEFAULT_SPEED, turnAmount);
	}
	public TurnThresh(boolean left){
		super(kP, kI, kD);
		setTarget(left ? 90 : -90);
		setSpeed(DEFAULT_SPEED);
	}

	private void setSpeed(double newSpeed){
		this.speed = newSpeed;
	}
	private void setTarget(double newTarget){
		this.target = newTarget;
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
		getPIDController().setOutputRange(-1, 1);
		getPIDController().setAbsoluteTolerance(THRESHOLD);
		getPIDController().setContinuous(true);
		double dest = Math.abs((Robot.navx.getYaw() + target + 180) % 360) - 180;
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
		Robot.drive.control(0, Math.signum(output) * speed);
	}

	@Override
	protected boolean isFinished() {
		return (getPIDController().onTarget() || (Robot.navx.getYaw() - this.getSetpoint()) >= THRESHOLD); //Not using finished_past and such because this Threshold
	}
}
