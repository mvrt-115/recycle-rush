package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.recyclerush.RobotMap;
import org.usfirst.frc.team115.recyclerush.commands.RollerArcadeWithJoystick;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem containing roller motors
 * @author Lee Mracek
 */
public class Roller extends Subsystem {

    private CANTalon leftMotor, rightMotor;
    private RobotDrive rollerDrive;

    public Roller() {
        leftMotor = new CANTalon(RobotMap.ROLLER_MOTOR_LEFT);
        rightMotor = new CANTalon(RobotMap.ROLLER_MOTOR_RIGHT);

        rollerDrive = new RobotDrive(leftMotor, rightMotor);

        rollerDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        rollerDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);

        leftMotor.enableLimitSwitch(false, false);
        rightMotor.enableLimitSwitch(false, false);
    }

    public void control(double move, double rotate) {
        rollerDrive.arcadeDrive(move, -rotate);
    }

    public void stop() {
        rollerDrive.arcadeDrive(0, 0);
    }

    public void log() {
        // TODO find something to log
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new RollerArcadeWithJoystick());
    }
}
