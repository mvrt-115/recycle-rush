package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team115.recyclerush.Robot;

/**
 * This command closes the robot's claw
 * @author MVRT
 */
public class CloseClaw extends Command {

    public CloseClaw() {
        requires(Robot.claw);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        Robot.claw.close();
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
