package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 * Moves the elevator to a specified height
 * @author Akhil Palla
 */
public class ElevatorToHeight extends PIDCommand {
    
    private static final double MAX_SPEED = 0.5; //for testing purposes. After testing, slowly increase
    private static final double FINISHED_THRESHOLD = 1; //command finishes when the elev height is less than (destHeight + FINISHED_THRESHOLD) inches
    
    Elevator elev;
    double destHeight;
    
    private static double P = 0, I = 0, D = 0;

    public ElevatorToHeight(double destHeight) {
        super(P, I, D);
        requires(Robot.elevator);
        this.destHeight = destHeight;
    }

    @Override
    protected void initialize() {
    	elev = Robot.elevator;
		elev.release();
		getPIDController().setOutputRange(-1 * MAX_SPEED, MAX_SPEED);
		setInputRange(Elevator.TOP_HEIGHT, Elevator.BOTTOM_HEIGHT);
		getPIDController().setAbsoluteTolerance(FINISHED_THRESHOLD);
		getPIDController().setSetpoint(destHeight);
    }

    @Override
    protected void execute() {}

    @Override
    protected boolean isFinished() {
    	return getPIDController().onTarget();
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

    @Override
    protected double returnPIDInput() {
        return elev.getHeight();
    }

    @Override
    protected void usePIDOutput(double output) {
        elev.control(output);
    }
}
