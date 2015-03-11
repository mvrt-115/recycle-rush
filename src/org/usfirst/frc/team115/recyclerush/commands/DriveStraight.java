package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Akhil Palla
 */
public class DriveStraight extends PIDCommand {

    //TODO Set actual PID Values
    private static final double P = 0.1;
    private static final double I = 0.1;
    private static final double D = 0.1;
    
    private static final double MAX_SPIN = 0.3;
    
    private double speed;
    private boolean useJoystick;
    
    DriveTrain drive;
    
    public DriveStraight(double speed, boolean joystick){
        super(P, I, D);
        
        if(speed < 0 || speed > 1)  
            throw new IllegalArgumentException();
        this.speed = speed;
        useJoystick = joystick;
        
        requires(Robot.drive);
        setInputRange(0, 360);
        getPIDController().setOutputRange(-1 * MAX_SPIN, MAX_SPIN);
    }

    @Override
    protected void initialize() {
        drive = Robot.drive;
        setSetpoint(drive.getYaw());
    }

    @Override
    protected double returnPIDInput() {
        return drive.getYaw();
    }

    @Override
    protected void usePIDOutput(double out) {
        if(useJoystick)
            drive.drive(Robot.oi.getJoystick().getY(), out);
        else
            drive.drive(speed, out);
    }
    
    @Override
    protected void execute() {
        //DEBUG CODE, REMOVE AFTER TESTING
        SmartDashboard.putNumber("DriveStraight Setpoint", getSetpoint());
        SmartDashboard.putNumber("DriveStraight Speed", speed);
        SmartDashboard.putBoolean("DriveStraight with joystick?", useJoystick);
        SmartDashboard.putNumber("DriveStraight Current Angle", drive.getYaw());
    }

    
    @Override
    protected void end() {
        Robot.drive.stop();
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }


}
