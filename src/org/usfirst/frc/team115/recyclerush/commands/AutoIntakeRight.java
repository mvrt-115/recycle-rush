package org.usfirst.frc.team115.recyclerush.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoIntakeRight extends CommandGroup{
    public AutoIntakeRight(){
    	addSequential(new ElevatorUp(0.0,0.0,0.0));
        addSequential(new RotateToteClockwise());
        addSequential(new AutoIntake());
    }

}
