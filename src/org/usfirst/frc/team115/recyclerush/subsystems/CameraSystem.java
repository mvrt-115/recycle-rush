package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.recyclerush.commands.CameraDisplay;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CameraSystem extends Subsystem {

    private Image frame;
    private int session;

    public void initialize() {
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        session = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);
        NIVision.IMAQdxStartAcquisition(session);
    }
    
    public void predisplay() {
        NIVision.IMAQdxStopAcquisition(session);
        NIVision.IMAQdxStartAcquisition(session);
    }

    public void display() {
        NIVision.IMAQdxGrab(session, frame, 1);
        CameraServer.getInstance().setImage(frame);
    }

    public void end() {
        NIVision.IMAQdxStopAcquisition(session);
    }

    public void log() {
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new CameraDisplay());
    }
}
