package org.usfirst.frc.team115.recyclerush.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;


public class AutoIntakeLeft extends CommandGroup{
    public AutoIntakeLeft(){
    	addSequential(new ElevatorUp(0.0,0.0,0.0));
        addSequential(new RotateToteCounterClockwise());
        addSequential(new AutoIntake());
    }
}
