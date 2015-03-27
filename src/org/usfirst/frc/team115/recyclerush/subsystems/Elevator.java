package org.usfirst.frc.team115.recyclerush.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team115.recyclerush.RobotMap;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorControl;
import org.usfirst.frc.team115.recyclerush.commands.ResetElevatorEncoder;

/**
 * A subsystem representing the elevator, which
 * lifts/lowers totes and bins
 *
 * @author Akhil Palla, Siddharth Gollapudi and Lee Mracek
 */
public class Elevator extends Subsystem {

    private CANTalon elevatorMotor1;
    private CANTalon elevatorMotor2;
    private DoubleSolenoid brakeSolenoid;

    // the following measurements are in inches:
    public static final double BOTTOM_HEIGHT = 57;
    public static final double TOP_HEIGHT = 0;
    public static final double MAX_SPEED_FINE = 1.0;

    //scaling values
    private static final int TICKS_PER_ROTATION = 1024;
    private static final double INCHES_PER_ROTATION = 3.53559055;
    private static final double TICKS_PER_INCH = TICKS_PER_ROTATION / INCHES_PER_ROTATION;

    public static final double THRESHOLD = 1;
    public static final double PRESET_SPEED = 0.5;

    public static final double PRESET_BOTTOM = 0;
    public static final double PRESET_TOTE_INTAKETOTE = 14;
    public static final double PRESET_BIN_INTAKETOTE = 24;
    public static final double PRESET_TOTE_INTAKETHREETOTES = 41;
    public static final double PRESET_TOP = 52;

    public static final double[] presets = {PRESET_BOTTOM, PRESET_TOTE_INTAKETOTE, PRESET_BIN_INTAKETOTE,
            PRESET_TOTE_INTAKETHREETOTES, PRESET_TOP};

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ElevatorControl());
    }

    public void initialize() {
        brakeSolenoid = new DoubleSolenoid(RobotMap.PCM, RobotMap.BRAKE_PORT_A, RobotMap.BRAKE_PORT_B);
        elevatorMotor1 = new CANTalon(RobotMap.ELEV_MOTOR_1);
        elevatorMotor2 = new CANTalon(RobotMap.ELEV_MOTOR_2);

        elevatorMotor1.setReverseSoftLimit((int) (0.25 * TICKS_PER_INCH));//set limit to (1 inch from top)
        elevatorMotor1.enableForwardSoftLimit(false);
        elevatorMotor1.enableReverseSoftLimit(true);
        elevatorMotor1.enableLimitSwitch(true, false);
        elevatorMotor1.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogEncoder);

        elevatorMotor2.changeControlMode(CANTalon.ControlMode.Follower);
        elevatorMotor2.set(elevatorMotor1.getDeviceID());

        new ElevResetTrigger().whenActive(new ResetElevatorEncoder());
    }

    public boolean isLimitPressed() {
        return elevatorMotor1.isFwdLimitSwitchClosed();
    }

    public double getHeight() {
        return BOTTOM_HEIGHT - elevatorMotor1.getPosition() / TICKS_PER_INCH;
    }

    public void resetEncoder() {
        elevatorMotor1.setPosition(BOTTOM_HEIGHT * TICKS_PER_INCH);
    }

    public void brake() {
        brakeSolenoid.set(Value.kForward);
    }

    public void stop() {
        control(0);
        brake();
    }

    public void unBrake() {
        brakeSolenoid.set(Value.kReverse);
    }

    public void control(double y_axis) {
        if (Math.abs(y_axis) - 1 > 0) throw new IllegalArgumentException("Axis must be between -1 and 1");
        elevatorMotor1.set(y_axis * MAX_SPEED_FINE);
    }

    public void log() {
        SmartDashboard.putBoolean("Elevator Limit", elevatorMotor1.isFwdLimitSwitchClosed());
        SmartDashboard.putNumber("Elevator Height", getHeight());
        SmartDashboard.putNumber("Elevator Height-ticks", elevatorMotor1.getPosition());
    }

    class ElevResetTrigger extends Trigger {

        @Override
        public boolean get() {
            return elevatorMotor1.isFwdLimitSwitchClosed();
        }

    }

}
