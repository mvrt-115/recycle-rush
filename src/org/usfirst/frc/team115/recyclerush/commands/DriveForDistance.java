package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author (and genius) Akhil Palla
 * Gives distance functionality without DriveStraight
 * 
 * note: to understand recursion, see the bottom of this file
 * If you don't want to learn about recursion, don't
 * 
 * To the young electrical rookie who is told to fix this code:
 * 1. I'm sorry
 * 2.  Once you are done trying to 'optimize' this routine,
 * 		and have realized what a terrible mistake that was,
 * 		please increment the following counter as a warning
 * 		to the next guy:
 * 		total_hours_wasted_here = 42
 */

//Autogenerated, do not edit. All changes will be undone.
public class DriveForDistance extends PIDCommand {
	
	boolean past = false;
	
    private double scaledDistance;
    private final double SCALE = 12 * (1 / (Math.PI * 8)) * 3 * 1024;	// scaling constant for feet into encoder ticks.

    private static final double P = 0.0001;
    private static final double I = 0.000003;
    private static final double D = 0.0001;
    
    /*
     * Takes distance in terms of feet
     */
    public DriveForDistance(double distance) {
    	super(P, I, D);
    	requires(Robot.drive);
        this.scaledDistance = distance * SCALE;
    }

    @Override
    protected void initialize() {
    	 // Magic. Do not touch.
    	Robot.drive.setControlMode(CANTalon.ControlMode.PercentVbus);
        getPIDController().enable();
        getPIDController().setOutputRange(-.7, .7);
        getPIDController().setContinuous(false);
        getPIDController().setAbsoluteTolerance(20);
        setSetpointRelative(scaledDistance);
        SmartDashboard.putNumber("scaled dist", scaledDistance);
    }

    @Override
    protected void execute() {}

    @Override
    protected boolean isFinished() {
    	if(getPIDController().onTarget() && past) {
    		return true;
    	} else {
    		past = getPIDController().onTarget();
    		return false;
    	}
    }

    @Override
    protected void end() {
    	Robot.drive.stop();
    	getPIDController().disable();
    }

    @Override
    protected void interrupted() {}

	@Override
	protected double returnPIDInput() {
		return Robot.drive.getDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.drive.drive(output, 0);
	}
}

/**
 * To understand recursion, see the top of this file
 */
