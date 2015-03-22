package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Moves the elevator to a certain preset
 * @author Akhil Palla, Marcus Plutowski
 */
public class ElevatorToHeight extends Command {
    
	private double Speed = 1;
    private double destHeight;
    
    public ElevatorToHeight(double destHeight) {
        requires(Robot.elevator);
        this.destHeight = destHeight;
        Speed = 1;
    }
    public ElevatorToHeight(double destHeight, double BaseSpeed) {
        requires(Robot.elevator);
        this.destHeight = destHeight;
        Speed = BaseSpeed;
    }
    
    public void setDest(double dest){
        destHeight = dest;
    }

    @Override
    protected void initialize() {
        Robot.elevator.unBrake();
    }

    @Override
    protected void execute() {
    	double SlopeSpeed = Elevator.PRESET_SPEED * Speed*Math.min(0.8*(Math.abs(destHeight-Robot.elevator.getHeight())/5)+0.2, 1);
        if(destHeight < Robot.elevator.getHeight())
        {
            Robot.elevator.control(SlopeSpeed);
        }
        else
        {
            Robot.elevator.control(-SlopeSpeed);
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
