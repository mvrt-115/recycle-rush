package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Moves the elevator up a preset
 * @author Akhil Palla
 */
public class ElevatorUp extends Command {
	
    private static final double SPEED_UP = 0.5; //for testing purposes. After testing, slowly increase
    private static final double FINISHED_THRESHOLD = 1; //command finishes when the elev height is greater than (destHeight - FINISHED_THRESHOLD) inches
    
    Elevator elev;
    double destHeight;

    public ElevatorUp() {
        requires(Robot.elevator);
        requires(Robot.roller);
    }

    @Override
    protected void initialize() {
    	// enable PID
    	elev = Robot.elevator;
		elev.release();
		setPosition();
    }

    private void setPosition(){
    	int[] presets = elev.presets;
    	double height = elev.getHeight();
    	destHeight = presets[presets.length - 1];
    	for(int preset:presets) {
    		if(preset > height + 1) { // if the preset is above current height
    		    SmartDashboard.putNumber("Elev dest height", preset);
                destHeight = height; // set that preset to our destination
    			break;
    			
    		}
    	}
    	elev.goUp(SPEED_UP);
    }
    
    @Override
    protected void execute() {}

    @Override
    protected boolean isFinished() {
    	return elev.getHeight() >= destHeight - FINISHED_THRESHOLD;
    }

    @Override
    protected void end() {
    	elev.stop();
    	Robot.oi.rumbleXbox(RumbleType.kLeftRumble, 0.2, 300);
    	Robot.oi.rumbleXbox(RumbleType.kRightRumble, 0.2, 300);
    }

    @Override
    protected void interrupted() {
        end();
    }
    
}
