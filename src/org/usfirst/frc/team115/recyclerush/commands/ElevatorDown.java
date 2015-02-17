package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves the elevator down to the next preset
 * @author Akhil Palla (creds to Lee for being Lee)
 */
public class ElevatorDown extends Command {
    
    Elevator elev;

    public ElevatorDown() {
        requires(Robot.elevator);
    }

    @Override
    protected void initialize() {
    	//enable PID
    	elev = Robot.elevator;
    	elev.enable();
		elev.release();
		setPosition();
    }

    private void setPosition(){
    	int[] presets = elev.presets;
    	double height = elev.getHeight();
    	int destPreset = 0;
    	for(int i = presets.length - 1; i >= 0; i--){
    		if(presets[i] < height - 1){ // if the preset is below current height
    			destPreset = i; // set that preset to our destination
    			break;
    		}
    	}
    	elev.setSetpoint(presets[destPreset]);
    }
    
    @Override
    protected void execute() {
    }

    @Override
    protected boolean isFinished() {
    	return elev.onTarget();
    }

    @Override
    protected void end() {
    	// disable PWM
    	elev.disable();
    	elev.brake();
    }

    @Override
    protected void interrupted() {

    }
}
