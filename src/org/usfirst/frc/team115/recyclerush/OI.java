package org.usfirst.frc.team115.recyclerush;

import org.usfirst.frc.team115.recyclerush.commands.ArcadePrecisionDrive;
import org.usfirst.frc.team115.recyclerush.commands.CloseGrabber;
import org.usfirst.frc.team115.recyclerush.commands.OpenGrabber;
import org.usfirst.frc.team115.recyclerush.commands.ToggleClaw;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

 /**
 * This class contains all interactions between physical controls and the robot,
 * including Xbox, Joystick, Triggers, etc.
 * @author MVRT
 **/
public class OI {
	
    private Joystick joystick, joystickLeft, joystickRight;
	private Joystick xbox;

    public OI() {
        joystick = new Joystick(RobotMap.JOYSTICK);
        joystickLeft = new Joystick(RobotMap.JOYSTICK_LEFT);
        joystickRight = new Joystick(RobotMap.JOYSTICK_RIGHT);

        //todo: map buttons for commands (@akhil99)
        xbox = new Joystick(RobotMap.XBOX);
		initXboxController();
		initJoystick();
    }
	
	
	private void initJoystick(){
		JoystickButton trigButton = new JoystickButton(joystick, RobotMap.JOYSTICK_THUMB);
		trigButton.whileHeld(new ArcadePrecisionDrive());
	}
	
	private void initXboxController(){
		//open grabber on LB press, close on RB
		JoystickButton lb = new JoystickButton(xbox, RobotMap.XBOX_LB);
		lb.whenPressed(new OpenGrabber());
		JoystickButton rb = new JoystickButton(xbox, RobotMap.XBOX_RB);
		rb.whenPressed(new CloseGrabber());
		
		//toggle claw/stabilizer on (y) button press
		JoystickButton y = new JoystickButton(xbox, RobotMap.XBOX_Y);
		y.whenPressed(new ToggleClaw());
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
	
	public Joystick getXbox() {
		return xbox;
	}
	
	public boolean getXboxButton(int btn){
		return xbox.getRawButton(btn);
	}
	
	public boolean getJoystickButton(int btn){
		return joystick.getRawButton(btn);
	}
	
	public double getXboxAxis(int axis){
		return xbox.getRawAxis(axis);
	}
	
	public double getJoystickAxis(int axis){
		return joystick.getRawAxis(axis);
	}
	
	public int getXboxPOV(){
		return xbox.getPOV();
	}

}

class XboxTrigger extends Trigger{
    
	Joystick xbox;
	int channel;
	double threshold;
	
	public XboxTrigger(Joystick xbox, int channel, double threshold) {
		this.xbox = xbox;
		this.channel = channel;
	}
	
	public boolean get() {
		return xbox.getRawAxis(channel) >= threshold;
	}
}

