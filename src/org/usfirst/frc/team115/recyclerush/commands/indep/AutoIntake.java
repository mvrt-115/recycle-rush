package org.usfirst.frc.team115.recyclerush.commands.indep;

import org.usfirst.frc.team115.recyclerush.commands.ClawClose;
import org.usfirst.frc.team115.recyclerush.commands.Delay;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorHardReset;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorToHeight;
import org.usfirst.frc.team115.recyclerush.commands.IntakeClose;
import org.usfirst.frc.team115.recyclerush.commands.IntakeOpen;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoIntake extends CommandGroup {

    public AutoIntake(double heightAtEnd, boolean intake, boolean ramp) {
        if(intake) {
            if(ramp) {
                addSequential(new IntakeClose());
            }
            addSequential(new IntakeTote());
        }
        addParallel(new ClawDelayedOpenCommand(Elevator.PRESET_AUTOINTAKE_OPEN));
        addSequential(new ElevatorHardReset());
        addSequential(new ClawClose());
        addSequential(new Delay(0.3));
        if(ramp) {
            addParallel(new IntakeOpen());
        }
        addSequential(new ElevatorToHeight(heightAtEnd));
    }
}
