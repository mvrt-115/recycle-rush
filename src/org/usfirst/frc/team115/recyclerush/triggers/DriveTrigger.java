package org.usfirst.frc.team115.recyclerush.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;
import org.usfirst.frc.team115.recyclerush.Robot;

/**
 * Created by Lee Mracek on 1/29/15.
 */
public class DriveTrigger extends Trigger {

    @Override
    public boolean get() {
        return !Robot.drive.getSelector().equals(Robot.drive.getCurrentCommand());
    }
}
