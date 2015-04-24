package org.usfirst.frc.team115.recyclerush.auton;

import org.usfirst.frc.team115.recyclerush.commands.ClawClose;
import org.usfirst.frc.team115.recyclerush.commands.DriveStraightForDistance;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorToHeight;
import org.usfirst.frc.team115.recyclerush.commands.StabilizerClose;
import org.usfirst.frc.team115.recyclerush.commands.StabilizerOpen;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftCanAuton extends CommandGroup {
    public LeftCanAuton() {
        addSequential(new ClawClose());
        addSequential(new StabilizerOpen());
        addSequential(new ElevatorToHeight(Elevator.PRESET_TOP - 10));
        addSequential(new StabilizerClose());
        addSequential(new DriveStraightForDistance(-110));
        addSequential(new Turn(-90));
        addSequential(new StabilizerOpen());
    }
}
