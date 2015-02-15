package org.usfirst.frc.team115.recyclerush;

import org.usfirst.frc.team115.recyclerush.commands.ArcadeDriveWithJoystick;
import org.usfirst.frc.team115.recyclerush.commands.DriveForDistance;
import org.usfirst.frc.team115.recyclerush.commands.DriveForTime;
import org.usfirst.frc.team115.recyclerush.commands.DriveStraightForTime;
import org.usfirst.frc.team115.recyclerush.commands.DriveStraightWithJoystick;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class contains all interactions between physical controls and the robot,
 * including Joystick, Triggers, etc.
 * @author MVRT
 */
public class OI {
    private Joystick joystick;
    private JoystickButton driveDistButton, driveTimeButton, driveStraightTimeButton;

    public OI() {
        joystick = new Joystick(RobotMap.JOYSTICK);
        driveDistButton = new JoystickButton(joystick , 11);
        driveTimeButton = new JoystickButton(joystick, 12);
    }


    public Joystick getJoystick() {
        return joystick;
    }
}

