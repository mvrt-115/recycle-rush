package org.usfirst.frc.team115.recyclerush.auton;

import org.usfirst.frc.team115.recyclerush.commands.ClawClose;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorToHeight;
import org.usfirst.frc.team115.recyclerush.commands.StabilizerClose;
import org.usfirst.frc.team115.recyclerush.commands.StabilizerOpen;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AllianceAuton extends CommandGroup {
    public AllianceAuton() {
        addSequential(new ClawClose());
        addSequential(new StabilizerOpen());
        addSequential(new ElevatorToHeight(Elevator.PRESET_TOP - 4));
        addSequential(new StabilizerClose());
    }
}
