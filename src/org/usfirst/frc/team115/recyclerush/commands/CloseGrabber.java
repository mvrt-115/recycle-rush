package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team115.recyclerush.Robot;

/**
 * Closes the robot's grabber
 * @author MVRT
 */
public class CloseGrabber extends Command {

	boolean finished = false;
	
    public CloseGrabber() {
        requires(Robot.grabber);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        Robot.grabber.close();
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
