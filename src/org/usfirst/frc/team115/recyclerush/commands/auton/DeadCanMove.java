package org.usfirst.frc.team115.recyclerush.commands.auton;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.commands.AutoIntake;
import org.usfirst.frc.team115.recyclerush.commands.DeadTurn;
import org.usfirst.frc.team115.recyclerush.commands.DriveDistance;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorHardReset;
import org.usfirst.frc.team115.recyclerush.commands.OpenClaw;
import org.usfirst.frc.team115.recyclerush.commands.RollerOpen;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class DeadCanMove extends CommandGroup {
	public DeadCanMove(){
		addSequential(new CanPickup());
		addSequential(new DeadTurn());
		addSequential(new DriveDistance(7.5));
		addSequential(new ElevatorHardReset());
		addSequential(new OpenClaw());
		addParallel(new RollerOpen());
		addSequential(new DriveDistance(-1));
	}
}
