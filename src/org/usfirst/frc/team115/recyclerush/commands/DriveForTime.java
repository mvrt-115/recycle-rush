package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team115.recyclerush.Robot;

/**
 * Created by Lee Mracek on 2/9/15.
 * Drives for time (without DriveStraight functionality)
 */
public class DriveForTime extends Command {
	double seconds;
    public DriveForTime(double seconds) {
    	requires(Robot.drive);
    	this.seconds = seconds;
    }

    @Override
    protected void initialize() {
        this.setTimeout(seconds);
    }

    @Override
    protected void execute() {
        Robot.drive.drive(Robot.drive.DEFAULT_VBUS, 0);
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

    @Override
    protected void end() {
        Robot.drive.stop();
    }

    @Override
    protected void interrupted() {
        Robot.drive.stop();
    }
}
