package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Created by Lee Mracek on 2/9/15.
 * Gives distance functionality without DriveStraight
 */
public class DriveForDistance extends Command {
	
	private double currentDistance; 

    private double scaledDistance;
    private final double SCALE = 1536.0 / Math.PI;	// scaling constant for feet into encoder ticks.

    /*
     * Takes distance in terms of feet
     */
    public DriveForDistance(double distance) {
        this.scaledDistance = distance * SCALE;
    }

    @Override
    protected void initialize() {
    	currentDistance = Robot.drive.getDistance();
        Robot.drive.setControlMode(CANTalon.ControlMode.PercentVbus);
    }

    @Override
    protected void execute() {
    	Robot.drive.drive(DriveTrain.DEFAULT_VBUS, 0);
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(Robot.drive.getDistance() - currentDistance) >= scaledDistance;
    }

    @Override
    protected void end() {
    	Robot.drive.stop();
    }

    @Override
    protected void interrupted() {

    }
}
