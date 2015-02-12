package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team115.recyclerush.Robot;

/**
 * This command closes the robot's claw
 * @author MVRT
 */
public class CloseClaw extends Command {

	boolean finished = false;
	
    public CloseClaw() {
        requires(Robot.claw);
        SmartDashboard.putBoolean("ClawClose", false);
    }

    @Override
    protected void initialize() {}

    @Override
    protected void execute() {
        Robot.claw.close();
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
