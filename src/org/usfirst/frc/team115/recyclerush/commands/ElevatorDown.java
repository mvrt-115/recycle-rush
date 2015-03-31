package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Moves the elevator down to the next preset
 * @author Akhil Palla
 */
public class ElevatorDown extends CommandGroup {

	ElevatorToHeight elevToHeight;

	public ElevatorDown() {
		elevToHeight = new ElevatorToHeight(0);
		addSequential(elevToHeight);
	}

	@Override
	public void initialize(){
		double destHeight = getGoal();
		if(destHeight == 0) {
			elevToHeight.cancel();
			addSequential(new ElevatorHardReset());
		}
		elevToHeight.setDest(destHeight);
	}

	private double getGoal() {
		double[] presets = Elevator.presets;
		double height = Robot.elevator.getHeight();
		double destHeight = presets[0];
		int destPreset = 0;
		for(int i = presets.length - 1; i >= 0; i--){
			if(presets[i] < height - 1){ // if the preset is below current height
				destPreset = i; // set that preset to our destination
				break;
			}
		}
		destHeight = presets[destPreset];
		return destHeight;
	}
}
