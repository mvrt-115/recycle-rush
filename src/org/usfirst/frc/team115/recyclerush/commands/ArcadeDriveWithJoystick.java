package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team115.recyclerush.Robot;

/**
<<<<<<< HEAD
 * Drives the robot using the Arcade Drive control scheme
 * @author MVRT
=======
 * Arcade Drive with joystick.
 * @author Lee Mracek
>>>>>>> 17d1c34f3702e96485f091f68932be2880bd156a
 */

public class ArcadeDriveWithJoystick extends Command {
    public ArcadeDriveWithJoystick() {
        requires(Robot.drive);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        Robot.drive.drive(Robot.oi.getJoystick());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    	//stop the robot when this command ends
        Robot.drive.drive(0, 0);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
