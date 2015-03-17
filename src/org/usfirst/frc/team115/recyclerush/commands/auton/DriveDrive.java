package org.usfirst.frc.team115.recyclerush.commands.auton;

import org.usfirst.frc.team115.recyclerush.commands.DelayCommand;
import org.usfirst.frc.team115.recyclerush.commands.DriveStop;
import org.usfirst.frc.team115.recyclerush.commands.DriveStraightDistanceNoPID;
import org.usfirst.frc.team115.recyclerush.commands.OpenClaw;

import edu.wpi.first.wpilibj.command.CommandGroup;
/*
 * @author Marcus Plutowski
 * Drives, waits, drives
 */
public class DriveDrive extends CommandGroup {

	public DriveDrive() {
		addSequential(new DriveStraightDistanceNoPID(5));
		addSequential(new DriveStop());
		addSequential(new DriveStraightDistanceNoPID(5));
		// TODO Auto-generated constructor stub
	}
}