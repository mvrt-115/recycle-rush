package org.usfirst.frc.team115.recyclerush.commands.auton;

import org.usfirst.frc.team115.recyclerush.commands.AutoIntake;
import org.usfirst.frc.team115.recyclerush.commands.DriveDistance;
import org.usfirst.frc.team115.recyclerush.commands.DriveStraightDistanceNoPID;
import org.usfirst.frc.team115.recyclerush.commands.OpenClaw;
import org.usfirst.frc.team115.recyclerush.commands.RollerOpen;
import org.usfirst.frc.team115.recyclerush.commands.Turn;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;
import edu.wpi.first.wpilibj.command.CommandGroup;

/*
 * PICKS UP A TOTE AND DROPS IT OFF IN THE AUTO ZONE
 * @author Marcus Plutowski
 */

public class SingleToteAuton extends CommandGroup{
	public SingleToteAuton() {
		addSequential(new OpenClaw());
		addParallel(new RollerOpen());
		addSequential(new AutoIntake(true, Elevator.PRESET_BOTTOM));
		addSequential(new Turn(90.0));
		addSequential(new DriveDistance(11.0));
		addSequential(new OpenClaw());
		addParallel(new RollerOpen());
		addSequential(new DriveDistance(-1.2));
	}
}
