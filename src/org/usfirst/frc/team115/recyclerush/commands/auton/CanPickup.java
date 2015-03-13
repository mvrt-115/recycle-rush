package org.usfirst.frc.team115.recyclerush.commands.auton;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.commands.AutoIntake;
import org.usfirst.frc.team115.recyclerush.commands.OpenClaw;
import org.usfirst.frc.team115.recyclerush.commands.RollerOpen;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CanPickup extends CommandGroup {
	public CanPickup(){
		addSequential(new RollerOpen());
		addParallel(new OpenClaw());
		addSequential(new AutoIntake(true, Elevator.PRESET_TOTE_INTAKETOTE));
	}
}
