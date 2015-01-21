package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.PIDCommand;
import org.usfirst.frc.team115.recyclerush.Robot;

/**
 * Created by Lee Mracek on 1/20/15.
 */

public class DriveStraight extends PIDCommand {

    Gyro gyro;

    public DriveStraight() {
        super(0.0, 0.0, 0.0);
        requires(Robot.drive);

        gyro = new Gyro(0);
    }

    @Override
    protected double returnPIDInput() {
        return gyro.getAngle();
    }

    @Override
    protected void usePIDOutput(double output) {
        Robot.drive.drive(Robot.oi.getJoystick().getY(), output);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {

    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {

    }

    @Override
    protected void interrupted() {

    }
}
