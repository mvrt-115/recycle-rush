/**
 * This class contains all interactions between physical controls and the robot,
 * including Joystick, Triggers, etc.
 * @author MVRT
 */

package org.usfirst.frc.team115.recyclerush;

import org.usfirst.frc.team115.recyclerush.commands.ArcadePrecisionDrive;
import org.usfirst.frc.team115.recyclerush.commands.AutoIntake;
import org.usfirst.frc.team115.recyclerush.commands.CloseClaw;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorDown;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorUp;
import org.usfirst.frc.team115.recyclerush.commands.OpenClaw;
import org.usfirst.frc.team115.recyclerush.commands.RollerClose;
import org.usfirst.frc.team115.recyclerush.commands.RollerOpen;
import org.usfirst.frc.team115.recyclerush.commands.ToggleStabilizer;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * This class contains all interactions between physical controls and the robot,
 * including Xbox, Joystick, Triggers, etc.
 *
 * @author MVRT
 */

public class OI {

    private Joystick joystick;
    private Joystick xbox;

    public static final int ROLLERCONTROL_AXIS_X = RobotMap.XBOX_AXIS_RX;
    public static final int ROLLERCONTROL_AXIS_Y = RobotMap.XBOX_AXIS_RY;

    public static final int AXIS_CONTROL_ELEVATOR = RobotMap.XBOX_AXIS_LY;

    public void initialize() {
        joystick = new Joystick(RobotMap.JOYSTICK);
        xbox = new Joystick(RobotMap.XBOX);
        initXboxController();
        initJoystick();
    }

    private void initJoystick() {
        JoystickButton trigButton = new JoystickButton(joystick, RobotMap.JOYSTICK_TRIGGER);
        trigButton.whileHeld(new ArcadePrecisionDrive());
    }

    /**
     * Sets up each button of the Xbox controller, and performs a command when pressed,
     * held, etc.
     */
    private void initXboxController() {
        // open grabber on LB press, close on RB
        JoystickButton lb = new JoystickButton(xbox, RobotMap.XBOX_LB);
        lb.whenPressed(new OpenClaw());
        JoystickButton rb = new JoystickButton(xbox, RobotMap.XBOX_RB);
        rb.whenPressed(new CloseClaw());
        JoystickButton a = new JoystickButton(xbox, RobotMap.XBOX_A);
        a.whenPressed(new AutoIntake(true, Elevator.PRESET_TOTE_INTAKETOTE));
        JoystickButton b = new JoystickButton(xbox, RobotMap.XBOX_B);
        b.whenPressed(new AutoIntake(false, Elevator.PRESET_TOTE_INTAKETOTE));

        XboxTrigger lt = new XboxTrigger(xbox, RobotMap.XBOX_LT, 0.6);
        XboxTrigger rt = new XboxTrigger(xbox, RobotMap.XBOX_RT, 0.6);

        lt.whenActive(new RollerOpen());
        rt.whenActive(new RollerClose());

        // toggle claw/stabilizer on (y) button press
        JoystickButton y = new JoystickButton(xbox, RobotMap.XBOX_Y);

        //y.toggleWhenPressed(new ToggleStabilizer());

        POVTrigger povUp = new POVTrigger(xbox, 0);
        povUp.whenActive(new ElevatorUp());
        POVTrigger povDown = new POVTrigger(xbox, 180);
        povDown.whenActive(new ElevatorDown());
    }

    public Joystick getJoystick() {
        return joystick;
    }

    public Joystick getXbox() {
        return xbox;
    }

    public boolean getXboxButton(int btn) {
        return xbox.getRawButton(btn);
    }

    public boolean getJoystickButton(int btn) {
        return joystick.getRawButton(btn);
    }

    public double getXboxAxis(int axis) {
        return xbox.getRawAxis(axis);
    }

    public double getJoystickAxis(int axis) {
        return joystick.getRawAxis(axis);
    }

    public double getElevatorAxis() {
        return getXboxAxis(AXIS_CONTROL_ELEVATOR);
    }

    public int getXboxPOV() {
        return xbox.getPOV();
    }

    public void rumbleXbox(final RumbleType type, final double strength, final long millis) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                xbox.setRumble(type, (float) strength);
                try {
                    Thread.sleep(millis);
                } catch (InterruptedException e) {
                    System.err.println("Rumble sleep failed");
                }
                xbox.setRumble(type, 0);
            }
        }).start();
    }
}

class POVTrigger extends Trigger {

    Joystick xbox;
    int angle;

    public POVTrigger(Joystick xbox, int angle) {
        this.xbox = xbox;
        this.angle = angle;
    }

    @Override
    public boolean get() {
        return xbox.getPOV() == angle;
    }
}

class XboxTrigger extends Trigger {

    Joystick xbox;
    int channel;
    double threshold;

    public XboxTrigger(Joystick xbox, int channel, double threshold) {
        this.xbox = xbox;
        this.channel = channel;
        this.threshold = threshold;
    }

    public boolean get() {
        return xbox.getRawAxis(channel) >= threshold;
    }
}
