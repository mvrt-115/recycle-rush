package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team115.recyclerush.Robot;

/**
 * Created by Lee Mracek on 2/9/15.
 * DrivesStraight using Joystick
 */
public class DriveStraightWithJoystick extends PIDCommand {
    private Joystick joystick;

    public DriveStraightWithJoystick(Joystick joystick) {
        super(Robot.drive.getP(), Robot.drive.getI(), Robot.drive.getD());
        requires(Robot.drive);

        this.joystick = joystick;
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
        return false;
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
        Robot.drive.drive(joystick.getY(), output);
    }
}
