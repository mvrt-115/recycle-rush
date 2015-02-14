package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

/**
 * Created by Lee Mracek on 2/10/15.
 */
public class ElevatorDown extends Command {
    
	private final int indices;
    Elevator elev;

    public ElevatorDown(int indices) {
    	requires(Robot.elevator);
    	this.indices = indices;
    }

    public ElevatorDown() {
        this(1);
    }

    @Override
    protected void initialize() {
    	//enable PID
    	elev = Robot.elevator;
    	elev.enable();
		elev.setMotorControlMode(ControlMode.Position);
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
    	Robot.elevator.setSetpoint(presets[destPreset]);
    }
    
    @Override
    protected void execute() {
    }

    @Override
    protected boolean isFinished() {
    	return Robot.elevator.onTarget();
    }

    @Override
    protected void end() {
    	// disable PWM
    	Robot.elevator.disable();
    	Robot.elevator.brake();
    }

    @Override
    protected void interrupted() {

    }
}
