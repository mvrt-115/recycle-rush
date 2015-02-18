package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team115.recyclerush.Robot;

/**
 * Stops the drivetrain
 * @author MVRT
 */
public class DriveStop extends Command {

	boolean finished = false;
	
    public DriveStop() {
        requires(Robot.drive);
    }

    @Override
    protected void initialize() {}

    @Override
    protected void execute() {
    	Robot.drive.stop();
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
