package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team115.recyclerush.Robot;

/**
 * Created by Lee Mracek on 2/10/15.
 */
public class ElevatorDown extends Command {
    private int indices;
    private boolean finished = false;

    public ElevatorDown(int indices) {
        this.indices = indices;
    }

    public ElevatorDown() {
        this(1);
        requires(Robot.elevator);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        Robot.elevator.down(indices);
        finished = true;
    }

    @Override
    protected boolean isFinished() {
        return finished;
    }

    @Override
    protected void end() {

    }

    @Override
    protected void interrupted() {

    }
}
