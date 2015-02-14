package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import org.usfirst.frc.team115.recyclerush.Robot;

public class Turn extends PIDCommand {

    public Turn(double goal) {
        super(0, 0, 0);
        getPIDController().enable();
        getPIDController().setContinuous(true);
        getPIDController().setAbsoluteTolerance(2);
        setInputRange(0, 360);
        setGoal(goal);
    }
    
    private void setGoal(double goal){
    	double target = (Robot.drive.getYaw() + goal + 720)%360;
    	setSetpoint(target);
    }
    
    /**
     * @return the gyro's current angle
     */
    @Override
    protected double returnPIDInput() {
        return (Robot.drive.getYaw() + 360) % 360;
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
		
	}
	
    @Override
    protected void execute() {}
	
    /**
     * @returns if our PID algorithm has finished turning
     */
    @Override
    protected boolean isFinished() {
        return getPIDController().onTarget();
    }
	
    /**
     * Stops the robot
     */
    @Override
    protected void end() {
        Robot.drive.stop();
        getPIDController().disable();
    }

    /**
     * Calls end()
     */
    @Override
    protected void interrupted() {
        end();
    }

}
