package org.usfirst.frc.team115.recyclerush;

import org.usfirst.frc.team115.recyclerush.commands.ArcadeDriveWithJoystick;
import org.usfirst.frc.team115.recyclerush.commands.DriveStraight;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class contains all interactions between physical controls and the robot,
 * including Joystick, Triggers, etc.
 * @author MVRT
 */
public class OI {
    private Joystick joystick;
    private JoystickButton driveStraightButton, timeButton;

    public OI() {
        joystick = new Joystick(RobotMap.JOYSTICK);
        driveStraightButton = new JoystickButton(joystick, RobotMap.DRIVE_STRAIGHT_BUTTON);
        timeButton = new JoystickButton(joystick, 2);
        
        // Drive for 3 seconds TEST
        driveStraightButton.whenPressed(new DriveStraight(0.05, 0.008, 0.1, 0));
        driveStraightButton.whenReleased(new ArcadeDriveWithJoystick());
    }


    public Joystick getJoystick() {
        return joystick;
    }
}

