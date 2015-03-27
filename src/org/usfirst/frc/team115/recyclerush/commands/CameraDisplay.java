package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class CameraDisplay extends Command {

    public CameraDisplay() {
    	requires(Robot.camera);
        this.setRunWhenDisabled(true);
    }

    @Override
    protected void initialize() {
        //Robot.camera.predisplay();
    }

    @Override
    protected void execute() {
        //Robot.camera.display();
    	Timer.delay(0.005);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.camera.end();
    }

    @Override
    protected void interrupted() {
        end();
    }

}
