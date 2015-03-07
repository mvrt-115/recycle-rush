package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Moves the elevator down to the next preset
 * @author Akhil Palla
 */
public class ElevatorDown extends Command {
    
    private static final double SPEED_DOWN = 0.5; //for testing purposes. After testing, slowly increase
    private static final double FINISHED_THRESHOLD = 1; //command finishes when the elev height is less than (destHeight + FINISHED_THRESHOLD) inches
    
    Elevator elev;
    double destHeight;

    public ElevatorDown() {
        requires(Robot.elevator);
    }

    @Override
    protected void initialize() {
    	//enable PID
    	elev = Robot.elevator;
		elev.release();
		setPosition();
    }

    private void setPosition(){
    	int[] presets = elev.presets;
    	double currHeight = elev.getHeight();
    	destHeight = presets[0];
    	for(int height:presets){
    		if(height < currHeight - 1){ // if the preset is below current height
    			SmartDashboard.putNumber("Elev dest height", height);
    			destHeight = height; // set that preset to our destination
    		}
    	}
    	elev.goDown(SPEED_DOWN);
    }
    
    @Override
    protected void execute() {}

    @Override
    protected boolean isFinished() {
    	return elev.getHeight() <= destHeight + FINISHED_THRESHOLD; //lower than (1 inch above dest)
    }

    @Override
    protected void end() {
    	elev.stop(); //hammertime!
    	Robot.oi.rumbleXbox(RumbleType.kLeftRumble, 0.2, 300);
    	Robot.oi.rumbleXbox(RumbleType.kRightRumble, 0.2, 300);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
