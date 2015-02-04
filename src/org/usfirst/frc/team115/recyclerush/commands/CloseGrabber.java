package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team115.recyclerush.Robot;

public class CloseGrabber extends Command {

    public CloseGrabber() {
        requires(Robot.grabber);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        Robot.grabber.close();
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
