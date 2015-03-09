package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoIntake extends CommandGroup{
    
    public AutoIntake(double upHeightAfterwards){
        addSequential(new IntakeTote());
        addParallel(new OpenClawDelay(0.2));
        addSequential(new ElevatorToHeight(0));
        addSequential(new CloseClaw());
        addSequential(new ElevatorToHeight(upHeightAfterwards));
    }
    
    class OpenClawDelay extends CommandGroup{
        
        public OpenClawDelay(double seconds){
            addSequential(new DelayCommand(seconds));
            addSequential(new OpenClaw());
        }
        
    }
}
