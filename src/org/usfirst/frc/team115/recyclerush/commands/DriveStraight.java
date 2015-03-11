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
    public static final double P = 0.1;
    public static final double I = 0.1;
    public static final double D = 0.1;
    
    private static final double MAX_SPIN = 0.3;
    
    private double speed;
    private boolean useJoystick;
    
    DriveTrain drive;
    
    public DriveStraight(double speed, boolean joystick){
        super(P, I, D);
        
        if(speed < -1 || speed > 1)  
            throw new IllegalArgumentException();
        this.speed = speed;
        useJoystick = joystick;
        
        requires(Robot.drive);
        setInputRange(0, 360);
        getPIDController().setOutputRange(-1 * MAX_SPIN, MAX_SPIN);
    }
    
    /**
     * Sets DriveStraight to drive at a specified speed
     * Also disables the option to set speed using joystick,
     * if that is currently enabled
     * @param spd: A speed (between -1 and 1) to drive at
     */
    public void setSpeed(double spd){
        speed = spd;
        useJoystick = false;
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
