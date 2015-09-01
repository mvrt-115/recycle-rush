package com.usfirst.frc.team115.lib.trajectory;
import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SampleVelocity extends Command {

    double dt = 1; //ms

    double elapsedT = 0;
    double elapsedX = 0;

    double initialTime = 0;
    double instVel = 0;
    double instAcc = 0;
    double avgVel = 0;
    double avgAcc = 0;
    double maxVel = 0;
    double maxAcc = 0;
    double pastVel = 0;

    double totAccErr = 0;
    double totVelErr = 0;

    private static int ENCODER_SCALE = 1444;
    private static double ERROR = 1;
    private final double SCALE = (1 / (Math.PI * 8)) * 2 * ENCODER_SCALE / ERROR; //ticks per inch

    public SampleVelocity() {
        setTimeout(7);
        this.initialTime = Timer.getFPGATimestamp();

    }

    @Override
    protected void initialize() {
        Robot.drive.resetEncoders();
        this.dt = 20;
    }

    @Override
    protected void execute() {
        this.dt = Timer.getFPGATimestamp() - this.elapsedT; //This is the unupdated elapsed time from the last loop
        this.elapsedT = Timer.getFPGATimestamp() - this.initialTime;
        this.elapsedX = Robot.drive.getDistance()/SCALE;

        this.instVel = Robot.drive.getVelocity();
        this.avgVel = elapsedX/elapsedT;

        this.instAcc = (instVel-pastVel)/dt;
        this.avgAcc = (this.avgAcc*(elapsedT-dt) + instAcc*dt)/elapsedT;

        this.maxAcc = maxAcc > instAcc ? instAcc : maxAcc;
        this.maxVel = maxVel > instVel ? instVel : maxVel;

        this.log();
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

    @Override
    protected void end() {
    }

    protected void log() {
        SmartDashboard.putNumber("Velocity", instVel);
        SmartDashboard.putNumber("Avg Velocity", avgVel);
        SmartDashboard.putNumber("Max Velocity", maxVel);

        //SmartDashboard.putNumber("Acceleration", instVel);
        //SmartDashboard.putNumber("Avg Acceleration", avgAcc);
        //SmartDashboard.putNumber("Acceleration", maxAcc);
    }
    @Override
    protected void interrupted() {
    }
}
