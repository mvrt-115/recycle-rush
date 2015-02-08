package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import org.usfirst.frc.team115.recyclerush.Robot;

public class Turn extends PIDCommand {

	private double goal;
    private double initial;

    public Turn(double goal) {
        super(0, 0, 0);

        this.goal = goal;

        initial = Robot.drive.getYaw();
    }
    
    /**
     * @return the gyro's current angle
     */
    @Override
    protected double returnPIDInput() {
        return Robot.drive.getYaw() - initial;
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
	protected void initialize() {}
	
    @Override
    protected void execute() {}
	
    /**
     * @returns whether the current angle is within 2 degrees of desired angle
     */
    @Override
    protected boolean isFinished() {
        if (Math.abs(goal - returnPIDInput()) <= 2) {
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
