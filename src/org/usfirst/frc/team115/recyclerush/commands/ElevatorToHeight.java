package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Moves the elevator down to the next preset
 * @author Akhil Palla (creds to Lee for being Lee)
 */
public class ElevatorToHeight extends Command {
    
    private double destHeight;
    
    public ElevatorToHeight(double destHeight) {
        requires(Robot.elevator);
        this.destHeight = destHeight;
    }

    @Override
    protected void initialize() {
        Robot.elevator.release();
    }

    @Override
    protected void execute() {
        if(destHeight < Robot.elevator.getHeight())
            Robot.elevator.control(Elevator.PRESET_SPEED);
        else 
            Robot.elevator.control(-1 * Elevator.PRESET_SPEED);
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(Robot.elevator.getHeight() - destHeight) <= Elevator.THRESHOLD;
    }

    @Override
    protected void end() {
        Robot.elevator.stop();
        System.out.println("Elev height ended. Target: " + destHeight + ", actual: " + Robot.elevator.getHeight());
        Robot.oi.rumbleXbox(RumbleType.kLeftRumble, 0.2, 300);
        Robot.oi.rumbleXbox(RumbleType.kRightRumble, 0.2, 300);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
