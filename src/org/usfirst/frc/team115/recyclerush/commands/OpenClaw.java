package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team115.recyclerush.Robot;

public class OpenClaw extends Command {

    public OpenClaw() {
        requires(Robot.claw);
        SmartDashboard.putBoolean("ClawOpen", true);
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
