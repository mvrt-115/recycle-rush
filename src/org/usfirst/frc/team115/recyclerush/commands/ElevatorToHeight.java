package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Moves the elevator to a certain preset
 * @author Akhil Palla
 */
public class ElevatorToHeight extends Command {
    
    private double destHeight;
    private boolean upwards = false;
    public static final double STOP_THRESHOLD = 1;
    public static final double RAMP_THRESHOLD = 3;
    public static final double EQUALIZATION_MULTIPLE = 0.6;
    double SlopeSpeed = -0.6;
    
    public ElevatorToHeight(double destHeight) {
        requires(Robot.elevator);
        setDest(destHeight);
    }
    
    public void setDest(double dest){
        destHeight = dest;
        if(dest < Robot.elevator.getHeight())
        {
        	upwards = true;
        	return;
        }
        upwards = false;
        return;
    }

    @Override
    protected void initialize() {
        Robot.elevator.unBrake();
    }

    @Override
    protected void execute() {
    	if(Math.abs(Robot.elevator.getHeight() - destHeight) < RAMP_THRESHOLD) {
    		SlopeSpeed = -0.6*
    				(Math.abs(Robot.elevator.getHeight() - destHeight) /
    				RAMP_THRESHOLD * EQUALIZATION_MULTIPLE + (1-EQUALIZATION_MULTIPLE));
    	}
    	else{
    		SlopeSpeed = -0.6;
    	}
        	if(Robot.elevator.getHeight() < destHeight){
        		Robot.elevator.control(SlopeSpeed);
        		SmartDashboard.putString("Driving", "Up");
        	}
        	else{SmartDashboard.putString("Driving", "Not");}
            if(Robot.elevator.getHeight() > destHeight){
        		Robot.elevator.control(-1 * SlopeSpeed);
        		SmartDashboard.putString("Driving", "Down");
        	}
            else{SmartDashboard.putString("Driving", "Not");}
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(Robot.elevator.getHeight() - destHeight) <= STOP_THRESHOLD;
    }

    @Override
    protected void end() {
        Robot.elevator.stop();
        Robot.oi.rumbleXbox(RumbleType.kLeftRumble, 0.2, 300);
        Robot.oi.rumbleXbox(RumbleType.kRightRumble, 0.2, 300);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
