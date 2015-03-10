package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;

public class DelayCommand extends Command{

    double time;
    
    public DelayCommand(double seconds){
        time = seconds;
    }

    @Override
    protected void initialize() {
        setTimeout(time);
    }

    @Override
    protected void execute() {
    }

    @Override
    protected boolean isFinished() {
       return isTimedOut();
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
        // TODO Auto-generated method stub
        
    }
    
}
