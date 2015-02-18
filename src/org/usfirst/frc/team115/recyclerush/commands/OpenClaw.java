package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team115.recyclerush.Robot;

/**
 * Opens the robot's grabber
 * @author MVRT
 */
public class OpenClaw extends Command {

	boolean finished = false;
	
    public OpenClaw() {
        requires(Robot.claw);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        Robot.claw.open();
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
