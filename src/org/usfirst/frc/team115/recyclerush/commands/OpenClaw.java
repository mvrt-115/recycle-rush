package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team115.recyclerush.Robot;

public class OpenClaw extends Command {

    public OpenClaw() {
        requires(Robot.claw);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        Robot.claw.open();
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
