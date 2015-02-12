package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team115.recyclerush.Robot;

/**
 * Opens the robot's grabber
 * @author MVRT
 */
public class OpenGrabber extends Command {

	boolean finished = false;
	
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
        finished = true;
    }

    @Override
    protected boolean isFinished() {
        return finished;
    }

    @Override
    protected void end() {}

    @Override
    protected void interrupted() {}

}
