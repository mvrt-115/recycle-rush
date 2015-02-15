package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team115.recyclerush.Robot;

/**
 * Created by Lee Mracek on 2/9/15.
 * Gives distance functionality without DriveStraight
 */
public class DriveForDistance extends Command {

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
        Robot.drive.setControlMode(CANTalon.ControlMode.Position);
        Robot.drive.setPosition(10);
    }

    @Override
    protected void execute() {

    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {

    }

    @Override
    protected void interrupted() {

    }
}
