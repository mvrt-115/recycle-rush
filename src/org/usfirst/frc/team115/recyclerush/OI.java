package org.usfirst.frc.team115.recyclerush;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import org.usfirst.frc.team115.recyclerush.commands.*;

/**
 * This class contains all interactions between physical controls and the robot,
 * including Joystick, Triggers, etc.
 * @author MVRT
 */
public class OI {
	
	private Joystick joystick;
	private Joystick xbox;
	
	public static final int AXIS_CONTROL_ELEVATOR = RobotMap.XBOX_AXIS_LY;
	
	public OI() {
		joystick = new Joystick(RobotMap.JOYSTICK);
		xbox = new Joystick(RobotMap.XBOX);
		initXboxController();
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
		
		//control elevator presets with POV/DPAD
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
	
	public boolean getXboxButton(int btn){
		return xbox.getRawButton(btn);
	}
	
	public double getXboxAxis(int axis){
		return xbox.getRawAxis(axis);
	}
	
	public double getElevatorAxis(){
		return getXboxAxis(AXIS_CONTROL_ELEVATOR);
	}
	
	public int getXboxPOV(){
		return xbox.getPOV();
	}	
}

class POVTrigger extends Trigger{
	
	Joystick xbox;
	int angle;
	
	public POVTrigger(Joystick xbox, int angle){
		this.xbox = xbox;
		this.angle = angle;
	}

	@Override
	public boolean get() {
		return xbox.getPOV() == angle;
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
