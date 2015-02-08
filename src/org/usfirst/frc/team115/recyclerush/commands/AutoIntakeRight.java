package org.usfirst.frc.team115.recyclerush.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoIntakeRight extends CommandGroup{
    public AutoIntakeRight(){
        //rotate tote right
        addSequential(new AutoIntake());
    }

}
