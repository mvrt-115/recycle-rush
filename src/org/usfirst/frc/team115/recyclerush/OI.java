package org.usfirst.frc.team115.recyclerush;

import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team115.recyclerush.commands.UpdateDriveType;
import org.usfirst.frc.team115.recyclerush.triggers.DriveTrigger;

/**
 * @author Lee Mracek
 *         This class should contain all interactions between physical controls and the robot,
 *         including Cypress, Joystick, Triggers, etc.
 */
public class OI {
    private Joystick joystick, joystickLeft, joystickRight;

    public OI() {

        joystick = new Joystick(RobotMap.JOYSTICK);
        joystickLeft = new Joystick(RobotMap.JOYSTICK_LEFT);
        joystickRight = new Joystick(RobotMap.JOYSTICK_RIGHT);

        //todo: map buttons for commands (@akhil99)
    }


    public Joystick getJoystick() {
        return joystick;
    }

    public Joystick getJoystickLeft() {
        return joystickLeft;
    }

    public Joystick getJoystickRight() {
        return joystickRight;
    }
}

