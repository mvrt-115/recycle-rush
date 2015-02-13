package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team115.recyclerush.Robot;

/**
 * Created by Lee Mracek on 1/29/15.
 */
public class TankDriveWithJoysticks extends Command {
    public TankDriveWithJoysticks() {
        requires(Robot.drive);
    }

    @Override
    protected void initialize() {}

    @Override
    protected void execute() {
        Robot.drive.tankDrive(Robot.oi.getJoystickLeft(), Robot.oi.getJoystickRight());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.drive.drive(0, 0);
    }

    @Override
    protected void interrupted() {
        Robot.drive.drive(0, 0);
    }
}
