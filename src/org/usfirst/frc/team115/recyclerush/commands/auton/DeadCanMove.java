package org.usfirst.frc.team115.recyclerush.commands.auton;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.commands.AutoIntake;
import org.usfirst.frc.team115.recyclerush.commands.DriveDistance;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorHardReset;
import org.usfirst.frc.team115.recyclerush.commands.OpenClaw;
import org.usfirst.frc.team115.recyclerush.commands.RollerOpen;
import org.usfirst.frc.team115.recyclerush.commands.Turn;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
/*
 * @author Marcus Plutowski
 * Picks up a can, turns, drives to middle, and drops it
 */
public class DeadCanMove extends CommandGroup {
	public DeadCanMove(){
		addSequential(new CanPickup());
		addSequential(new Turn(90));
		addSequential(new DriveDistance(7.5));
		addSequential(new ElevatorHardReset());
		addSequential(new OpenClaw());
		addParallel(new RollerOpen());
		addSequential(new DriveDistance(-1));
	}
}
