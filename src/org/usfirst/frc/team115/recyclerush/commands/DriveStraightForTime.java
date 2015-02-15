package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.DriveTrain;

/**
 * Created by Lee Mracek on 2/9/15
 * Replaces DriveStraight.java.
 */
public class DriveStraightForTime extends PIDCommand {

    public DriveStraightForTime(double seconds) {
        super(Robot.drive.getP(), Robot.drive.getI(), Robot.drive.getD());
        this.setTimeout(seconds);
        requires(Robot.drive);
    }

    @Override
    protected void initialize() {
        this.setSetpointRelative(0);
    }

    @Override
    protected void execute() {
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
        end();
    }

    @Override
    protected double returnPIDInput() {
        return Robot.drive.getYaw();
    }

    @Override
    protected void usePIDOutput(double output) {
        Robot.drive.drive(DriveTrain.DEFAULT_VBUS, output);
    }
}
