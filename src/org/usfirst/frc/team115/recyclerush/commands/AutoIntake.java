package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoIntake extends CommandGroup{
    
    public AutoIntake(){
        addSequential(new IntakeTote());
        addSequential(new ElevatorDown(0.0, 0.0, 0.0));
        addSequential(new OpenClaw());
        addSequential(new ElevatorDown(0.0, 0.0, 0.0));
        addSequential(new CloseClaw());
    }
}
