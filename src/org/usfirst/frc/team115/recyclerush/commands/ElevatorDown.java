package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Moves the elevator down to the next preset
 * @author Akhil Palla
 */
public class ElevatorDown extends CommandGroup {
	
    public ElevatorDown() {
        double destHeight = getGoal();
        addSequential(new ElevatorToHeight(destHeight));
    }

    private double getGoal() {
        double[] presets = Robot.elevator.presets;
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
