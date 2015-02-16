package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team115.recyclerush.Robot;

/**
 * This command opens the robot's claw
 * @author MVRT
 */
public class OpenStabilizer extends Command {

	boolean finished = false;
	
    public OpenStabilizer() {
        requires(Robot.stabilizer);
    }

    @Override
    protected void initialize() {}

    @Override
    protected void execute() {
        Robot.stabilizer.open();
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
