package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoRoller extends Command {
    
    public void AutoRoller() {
        requires(Robot.roller);
    }

    @Override
    protected void initialize() {
        
    }

    @Override
    protected void execute() {
        if (Robot.roller.getIntakeLimitSwitchLeft()) {
            Robot.roller.control(1 , 0);
        } else if (Robot.roller.getIntakeLimitSwitchRight()) {
            Robot.roller.control(-1 , 0);
        } else {
            Robot.roller.control(0 , 1);
        }
    }

    @Override
    protected boolean isFinished() {
        if (Robot.roller.getIntakeLimitSwitchLeft() && Robot.roller.getIntakeLimitSwitchRight()) {
            return true;
        }
        return false;
    }

    @Override
    protected void end() {
        Robot.roller.stop();
    }

    @Override
    protected void interrupted() {
        end();
    }
    
}
