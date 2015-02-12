package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import org.usfirst.frc.team115.recyclerush.Robot;

public class Turn extends PIDCommand {

	private double goal;
	private double target;
    
    private static final double P = 0;
    private static final double I = 0;
    private static final double D = 0;

    public Turn(double goal) {
        super(P, I, D);
        this.goal = goal;
        setInputRange(0, 360);
        // in our scaling method, angle is 
        // scaled such that 180 is the target
        setSetpoint(180);
    }
    
    /**
     * @return the gyro's current angle
     */
    @Override
    protected double returnPIDInput() {
        return (getGyroAngle() + 180 - target) % 360;
    }
	
	/**
	 * Drives the robot forward at the joystick's speed, and at the output angle.
	 * @param output angle to turn
	 */
	@Override
	protected void usePIDOutput(double output) {
		Robot.drive.drive(Robot.oi.getJoystick().getY(),  output);
	}

	@Override
	protected void initialize() {
		double initial = getGyroAngle();
		target = (initial + goal) % 360;
	}
	
	private double getGyroAngle(){
		return Robot.drive.getYaw() % 360;
	}
	
    @Override
    protected void execute() {}
	
    /**
     * @returns whether the current angle is within 2 degrees of desired angle
     */
    @Override
    protected boolean isFinished() {
        if (Math.abs(getGyroAngle() - target) <= 2) {
            return true;
        }
        return false;
    }
	
    /**
     * Stops the robot
     */
    @Override
    protected void end() {
        Robot.drive.stop();
    }

    /**
     * Calls end()
     */
    @Override
    protected void interrupted() {
        end();
    }

}
