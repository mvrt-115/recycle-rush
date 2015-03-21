package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.recyclerush.commands.CameraDisplay;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CameraSystem extends Subsystem {

    private Image frame;
    private int session;
    CameraServer camera;
    CameraServer server;
    public void initialize() {
       /* frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        session = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);*/
    	server = CameraServer.getInstance();
        server.setQuality(50);
        //the camera name (ex "cam0") can be found through the roborio web interface
        server.startAutomaticCapture("cam0");
    	//camera = CameraServer.getInstance();
    	//camera.setSize(50);
    	//CameraServer.getInstance().startAutomaticCapture("cam0");
    	////CameraServer.getInstance().serve();
    }
    
    public void predisplay() {
        //NIVision.IMAQdxStopAcquisition(session);
        //NIVision.IMAQdxStartAcquisition(session);
    }

    public void display() {
    	//NIVision.IMAQdxStartAcquisition(session);
        //NIVision.IMAQdxGrab(session, frame, 1);
        //CameraServer.getInstance().setQuality(1);
        //CameraServer.getInstance().setSize(100);
        //CameraServer.getInstance().setImage(frame);
        //CameraServer.getInstance().setQuality(1);
        //NIVision.IMAQdxStopAcquisition(session);
        
    }

    public void end() {
        //NIVision.IMAQdxStopAcquisition(session);
    }

    public void log() {
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new CameraDisplay());
    }
}
