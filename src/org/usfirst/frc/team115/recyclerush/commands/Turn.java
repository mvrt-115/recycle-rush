package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Turn extends PIDCommand {

    private double goal;
    private double initial;

    public Turn(double goal) {
        super(0, 0, 0);
    	requires(Robot.drive);
    	
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
     * @param output: angle to turn
     */
    @Override
    protected void usePIDOutput(double output) {
        SmartDashboard.putString("Chassis Current Angle", returnPIDInput()+"");
        Robot.drive.drive(Robot.oi.getJoystick().getY(), output);
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
    	SmartDashboard.putNumber("Chassis Gyro Angle Current", returnPIDInput());
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
