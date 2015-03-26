package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorHardReset extends Command{

    private static final double TIMEOUT_SAFETY = 3;
    
    public ElevatorHardReset() {
        requires(Robot.elevator);
    }
    
    @Override
    protected void initialize() {
       Robot.elevator.unBrake();
       setTimeout(TIMEOUT_SAFETY);
    }

    @Override
    protected void execute() {
       Robot.elevator.control(Elevator.PRESET_SPEED);
    }

    @Override
    protected boolean isFinished() {
        return Robot.elevator.isLimitPressed() || isTimedOut();
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
