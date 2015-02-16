package org.usfirst.frc.team115.recyclerush.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team115.recyclerush.RobotMap;

/**
 * A subsystem representing the robot's grabber, which opens/closes
 * to hold totes and bins
 * @author MVRT
 */
public class Grabber extends Subsystem {

    DoubleSolenoid grabberSolenoid;

    public Grabber() {
        super();
        grabberSolenoid = new DoubleSolenoid(RobotMap.PCM, RobotMap.GRABBER_SOLENOID_1, RobotMap.GRABBER_SOLENOID_2);
    }

    /**
     * Closes the grabber
     */
    public void close() {
        grabberSolenoid.set(Value.kReverse);
    }

    /**
     * Opens the grabber
     */
    public void open() {
        grabberSolenoid.set(Value.kForward);
    }

    /**
     * Sets the default command for this subsystem
     */
    @Override
    protected void initDefaultCommand() {}

}
