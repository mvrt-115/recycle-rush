package org.usfirst.frc.team115.recyclerush;

import org.usfirst.frc.team115.recyclerush.commands.DriveStraight;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * @author Lee Mracek
 *         This class should contain all interactions between physical controls and the robot,
 *         including Cypress, Joystick, Triggers, etc.
 */
public class OI {
    private Joystick joystick;
    private JoystickButton driveStraightButton;

    public OI() {

        joystick = new Joystick(RobotMap.JOYSTICK);
        driveStraightButton = new JoystickButton(joystick, RobotMap.DRIVE_STRAIGHT_BUTTON);
        
        driveStraightButton.whileHeld(new DriveStraight(0.5, 0, 0, 0));

        //todo: map buttons for commands (@akhil99)
    }


    public Joystick getJoystick() {
        return joystick;
    }
}

