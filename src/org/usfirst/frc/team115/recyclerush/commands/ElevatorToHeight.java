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
    public double Threshold = 0;
    double SlopeSpeed = 0.6;
    
    public ElevatorToHeight(double destHeight) {
        requires(Robot.elevator);
        this.destHeight = destHeight;
    }
    
    public void setDest(double dest){
        destHeight = dest;
        Threshold = 0.05*Math.abs(Robot.elevator.getHeight() - destHeight);
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
    	if(Math.abs(Robot.elevator.getHeight() - destHeight) < Math.abs(3*Threshold - destHeight)) {
    		SlopeSpeed = 0.6*Math.abs(Robot.elevator.getHeight() - destHeight)/Math.abs(3*Threshold-destHeight);
    	}
    	else{
    		SlopeSpeed = 0.6;
    	}
        if(upwards)
        {
            Robot.elevator.control(-1 * SlopeSpeed);
        }
        else
        {
            Robot.elevator.control(SlopeSpeed);
        }
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(Robot.elevator.getHeight() - destHeight) <= Elevator.THRESHOLD;
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
