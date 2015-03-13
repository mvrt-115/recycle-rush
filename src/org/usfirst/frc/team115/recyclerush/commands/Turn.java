package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Turn extends PIDCommand {

    private double goal;
    private double initial;
    public final static double CompP = 0.045; //Tested PID values for use in competition running
    public final static double CompI = 0.005;
    public final static double CompD = 0;

    boolean past = false;

    public Turn(double goal, final double P, final double I, final double D) { // Custom access to PID values
        super(P, I, D);
    	requires(Robot.drive);
        this.goal = goal;
    }

    public Turn(double goal) { // Pre-PID accessibility case for backwards compatibility and easy usage of function; just uses the hardcoded PID values we'll have tested
    	this(goal, CompP, CompI, CompD);
    }

    /**
     * @return the gyro's current angle
     */
    @Override
    protected double returnPIDInput() {
        SmartDashboard.putNumber("Yaw", Robot.drive.getYaw());
        return Robot.drive.getYaw();
    }

    /**
     * Drives the robot forward at the joystick's speed, and at the output angle.
     * @param output: angle to turn
     */
    @Override
    protected void usePIDOutput(double output) {
    	Robot.drive.drive(0, output);
    }

    @Override
    protected void initialize() {
    	getPIDController().setContinuous(true);
        setInputRange(0, 360);
        getPIDController().setOutputRange(-0.4, 0.4);
        getPIDController().setAbsoluteTolerance(10); //set 5 degree tolerance
    	initial = Robot.drive.getYaw();
    	setSetpoint(initial);
    	setSetpointRelative(goal);
    	System.out.println("goal: " + goal + ", initial: " + initial); //TODO remove debug
    }

    @Override
    protected void execute() {}

    /**
     * @returns whether the current angle is within 2 degrees of desired angle
     */
    @Override
    protected boolean isFinished() {
        if(past && getPIDController().onTarget()) {
        	SmartDashboard.putBoolean("Is Finished", true);
        	return true;
        } else {
        	past = getPIDController().onTarget();
        	return false;
        }
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
