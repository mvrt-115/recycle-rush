package org.usfirst.frc.team115.recyclerush.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team115.recyclerush.RobotMap;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorControl;

public class Elevator extends Subsystem {
	private CANTalon elevatorMotor;
	private DoubleSolenoid elevatorSolenoid;
	public static final double MAX_HEIGHT = 70.36; // in inches
	public static final double MIN_HEIGHT = 0;
	public static final double MAX_SPEED_FINE = 0.2;
	private static final double revLength = 3.18;	// in inches
    private static final double p = 0.0;
    private static final double i = 0.0;
    private static final double d = 0.0;

    public final double presets[] = {0, 12, 24, 36};
    private int presetIndex = 0;

	public Elevator() {
		elevatorSolenoid = new DoubleSolenoid(RobotMap.BRAKE_SOLENOID_1, RobotMap.BRAKE_SOLENOID_2);
        elevatorMotor = new CANTalon(RobotMap.ELEVATOR);
	}

    public void initialize() {
        elevatorMotor.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogEncoder);
        elevatorMotor.setPID(p, i, d);
    }

    //TODO change to inches
	public double getHeight() {
		return revLength * (elevatorMotor.getPosition() / 1023);
	}
	
	public void setHeight(double height) {
		elevatorMotor.changeControlMode(CANTalon.ControlMode.Position);
		elevatorMotor.enableControl();
		
		if(height < MIN_HEIGHT){
			height = MIN_HEIGHT;
		}else if(height < MAX_HEIGHT){
			height = MAX_HEIGHT;
		}

		elevatorMotor.set(height);
	}
	
	public void setHeightRelative(double increment) {
        setHeight(rotationsToInches(elevatorMotor.getPosition()) + increment);
	}

	public void stop() {
		elevatorMotor.disableControl();
		elevatorMotor.set(0);
	}
	
	public void brake() {
		elevatorSolenoid.set(Value.kForward);
	}
	
	public void release() {
		elevatorSolenoid.set(Value.kReverse);
	}
	
	public void control(double y_axis) {
		if(Math.abs(y_axis) - 1 > 0) throw new IllegalArgumentException("Axis must be between -1 and 1");
		elevatorMotor.changeControlMode(CANTalon.ControlMode.PercentVbus);
		elevatorMotor.set(y_axis * MAX_SPEED_FINE);
	}

    public void up(int indices) {
        setHeight(presetIndex + indices > presets.length - 1 ? presets[presets.length - 1] : presets[presetIndex + indices]);
    }

    public void down(int indices) {
        setHeight(presetIndex - indices < 0 ? presets[presets.length - 1] : presets[presetIndex - indices]);
    }
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ElevatorControl());
	}

    public boolean isBraking() {
        return elevatorSolenoid.get().equals(Value.kForward);
    }

    public static double rotationsToInches(double rotations) {

    }

    public static double inchesToRotations(double inches) {

    }
}

