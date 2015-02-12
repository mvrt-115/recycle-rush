package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team115.recyclerush.Robot;

/**
 * Created by Lee Mracek on 2/10/15.
 * Moves the elevator up a preset
 */
public class ElevatorUp extends Command {
    private boolean finished;
    private final int indices;

    public ElevatorUp() {
        this(1);
    }

    public ElevatorUp(int indices) {
        requires(Robot.elevator);
        this.indices = indices;
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        Robot.elevator.up(indices);
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
