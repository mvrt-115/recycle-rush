package org.usfirst.frc.team115.recyclerush;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class contains all interactions between physical controls and the robot,
 * including Joystick, Triggers, etc.
 * @author MVRT
 */
public class OI {
    private Joystick joystick;

    public OI() {
        joystick = new Joystick(RobotMap.JOYSTICK);
    }


    public Joystick getJoystick() {
        return joystick;
    }
}

