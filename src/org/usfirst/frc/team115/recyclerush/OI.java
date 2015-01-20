package org.usfirst.frc.team115.recyclerush;

import edu.wpi.first.wpilibj.Joystick;

/**
 * @author Lee Mracek
 * This class should contain all interactions between physical controls and the robot,
 * including Cypress, Joystick, Triggers, etc.
 */
public class OI {
    private Joystick joystick = new Joystick(RobotMap.MAIN_JOYSTICK);

    public Joystick getJoystick() {
        return joystick;
    }
}

