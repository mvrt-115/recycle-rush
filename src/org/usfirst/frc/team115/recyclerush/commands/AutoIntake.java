package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoIntake extends CommandGroup{
    
	 public AutoIntake(boolean intakeTote, double upHeightAfterwards){
	      this(intakeTote, upHeightAfterwards, true);
	    }
	
    public AutoIntake(boolean intakeTote, double upHeightAfterwards, boolean openArm){
        if (intakeTote) {
            addSequential(new IntakeTote());
        }

        addParallel(new CommandDelay(new OpenClaw(), 0.1));
        addSequential(new ElevatorHardReset());
        addSequential(new CloseClaw());
        addSequential(new DelayCommand(0.4)); // delay for claw to close
        if(openArm)addSequential(new CommandDelay(new RollerOpen(), 0.1));
        addSequential(new ElevatorToHeight(upHeightAfterwards));
    }
    
    class CommandDelay extends CommandGroup{

        public CommandDelay(Command c, double seconds){
            addSequential(new DelayCommand(seconds));
            addSequential(c);
        }
    }
}
