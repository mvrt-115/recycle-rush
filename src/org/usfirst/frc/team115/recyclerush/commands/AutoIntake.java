package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoIntake extends CommandGroup{
    
    public AutoIntake(){
        //addSequential(new ElevatorToHeight(14));
        addSequential(new IntakeTote());
        addSequential(new ElevatorToHeight(12));
        addSequential(new OpenClaw());
        addSequential(new ElevatorToHeight(0));
        addSequential(new CloseClaw());
        addSequential(new RollerOpen());
        addSequential(new DelayCommand(0.5));
        addSequential(new ElevatorUp());
    }
}
