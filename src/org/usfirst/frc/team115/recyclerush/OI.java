package org.usfirst.frc.team115.recyclerush;

import org.usfirst.frc.team115.recyclerush.commands.ArcadePrecisionDrive;
import org.usfirst.frc.team115.recyclerush.commands.CloseClaw;
import org.usfirst.frc.team115.recyclerush.commands.OpenClaw;
import org.usfirst.frc.team115.recyclerush.commands.RollerClose;
import org.usfirst.frc.team115.recyclerush.commands.RollerOpen;
import org.usfirst.frc.team115.recyclerush.commands.ToggleStabilizer;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

 /**
 * This class contains all interactions between physical controls and the robot,
 * including Xbox, Joystick, Triggers, etc.
 * @author MVRT
 **/
public class OI {
	
	private Joystick joystick;
	private Joystick xbox;
	
	public static final int ROLLERCONTROL_AXIS_X = RobotMap.XBOX_AXIS_RX;
	public static final int ROLLERCONTROL_AXIS_Y = RobotMap.XBOX_AXIS_RY;
	
	public OI() {
		joystick = new Joystick(RobotMap.JOYSTICK);
		xbox = new Joystick(RobotMap.XBOX);
		initXboxController();
		initJoystick();
	}
	
	private void initJoystick(){
		JoystickButton trigButton = new JoystickButton(joystick, RobotMap.JOYSTICK_THUMB);
		trigButton.whileHeld(new ArcadePrecisionDrive());
	}
	
	/**
	 * Sets up each button of the Xbox controller, and performs a command when pressed,
	 * held, etc.
	 * 
	 */
	private void initXboxController(){
		// open grabber on LB press, close on RB
		JoystickButton lb = new JoystickButton(xbox, RobotMap.XBOX_LB);
		lb.whenPressed(new OpenClaw());
		JoystickButton rb = new JoystickButton(xbox, RobotMap.XBOX_RB);
		rb.whenPressed(new CloseClaw());
	
		XboxTrigger lt = new XboxTrigger(xbox, RobotMap.XBOX_LT, 0.6);
		XboxTrigger rt = new XboxTrigger(xbox, RobotMap.XBOX_RT, 0.6);
		
		lt.whenActive(new RollerOpen());
		rt.whenActive(new RollerClose());
		
		// toggle claw/stabilizer on (y) button press
		JoystickButton y = new JoystickButton(xbox, RobotMap.XBOX_Y);
		y.toggleWhenPressed(new ToggleStabilizer());
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
	
	public double getElevAxis(){
		return getXboxAxis(RobotMap.XBOX_AXIS_LY);
	}
	
}

class XboxTrigger extends Trigger{

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

class DpadTrigger extends Trigger {
	
	Joystick xbox;
	int angle;
	
	public DpadTrigger(Joystick xbox, int angle){
		this.angle = angle;
		this.xbox = xbox;
	}
	
	public boolean get(){
		return xbox.getPOV() == angle;
	}
	
}
