package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team115.recyclerush.Robot;

public class OpenGrabber extends Command {

    public OpenGrabber() {
        requires(Robot.grabber);
        SmartDashboard.putBoolean("GrabberOpen", true);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        Robot.grabber.open();
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
