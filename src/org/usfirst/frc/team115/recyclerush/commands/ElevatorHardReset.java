package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorHardReset extends Command{

    public ElevatorHardReset() {
        requires(Robot.elevator);
    }
    
    @Override
    protected void initialize() {
       Robot.elevator.unBrake();
    }

    @Override
    protected void execute() {
       Robot.elevator.control(Elevator.PRESET_SPEED);
    }

    @Override
    protected boolean isFinished() {
        return Robot.elevator.isLimitPressed();
    }

    @Override
    protected void end() {
    	Robot.elevator.resetEncoder();
        Robot.elevator.stop();
    }

    @Override
    protected void interrupted() {
        end();
    }

    
    
}
