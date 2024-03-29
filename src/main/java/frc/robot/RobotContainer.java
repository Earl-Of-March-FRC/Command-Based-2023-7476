package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.DriverStationConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commandgroups.AutoCmds.Balance;
import frc.robot.commandgroups.AutoCmds.ScoreTopLeaveClose;
import frc.robot.commandgroups.TeleopAlign;
import frc.robot.commandgroups.TeleopArm.ArmDefaultPosition;
import frc.robot.commandgroups.TeleopArm.ArmLoadPieces;
import frc.robot.commandgroups.TeleopArm.ArmPlaceLow;
import frc.robot.commandgroups.TeleopArm.ArmPlaceMid;
import frc.robot.commandgroups.TeleopArm.ArmPlaceTop;
import frc.robot.commands.Arm.ArmControl;
import frc.robot.commands.Arm.CancelArmAutomation;
import frc.robot.commands.ClawControl;
import frc.robot.commands.Drivetrain.CancelDriveAutomation;
import frc.robot.commands.Drivetrain.GyroTurnAnglePID;
import frc.robot.commands.Drivetrain.MecanumDriveCmd;
// import frc.robot.commands.Drivetrain.VerticalDrivePID;
import frc.robot.commands.GyroReset;
// import frc.robot.commandgroups.AutoCmds.ScoreFloorAndBalance;
import frc.robot.commands.Limelight.LimelightLEDControl;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class RobotContainer {

  final DriveTrainSubsystem driveSubsystem = new DriveTrainSubsystem();
  public final Arm armMotors = new Arm();
  public final Claw claw = new Claw();

  public final VisionSubsystem vision = new VisionSubsystem();
  public final LEDSubsystem led = new LEDSubsystem();

  public final Joystick controller = new Joystick(OperatorConstants.kDriverControllerPort);

  public final Joystick driverStation =
      new Joystick(DriverStationConstants.DriverStationController);

  public Command armPlaceTopCommand = new ArmPlaceTop(armMotors, led);
  public Command armPlaceMidCommand = new ArmPlaceMid(armMotors, led);
  public Command armPlaceLowCommand = new ArmPlaceLow(armMotors, led);
  public Command armdefaultCommand = new ArmDefaultPosition(armMotors, led);
  public Command armLoadCmd = new ArmLoadPieces(armMotors, led);

  public Command gyroTurn = new GyroTurnAnglePID(driveSubsystem, 0);

  public Command ConeAlign = new TeleopAlign(vision, driveSubsystem, led, 0);
  public Command CubeAlign = new TeleopAlign(vision, driveSubsystem, led, 1);

  public Command Balance = new Balance(driveSubsystem);

  public Command GyroTurn = new GyroTurnAnglePID(driveSubsystem, 0);

  public JoystickButton triggerButton = new JoystickButton(controller, 1);

  public RobotContainer() {

    driveSubsystem.setDefaultCommand(
        new MecanumDriveCmd(
            driveSubsystem,
            () -> controller.getRawAxis(OperatorConstants.sideAxis),
            () -> controller.getRawAxis(OperatorConstants.forwardAxis),
            () -> controller.getRawAxis(OperatorConstants.rotationAxis),
            () -> controller.getRawAxis(OperatorConstants.scaleAxis),
            () -> triggerButton.getAsBoolean()));

    armMotors.setDefaultCommand(
        new ArmControl(
            armMotors,
            () -> driverStation.getRawAxis(DriverStationConstants.ArmExtendAxis),
            () -> driverStation.getRawAxis(DriverStationConstants.ArmInclineAxis)));

    configureBindings();
  }

  private void configureBindings() {

    // Open Claw
    new JoystickButton(driverStation, DriverStationConstants.ClawOpenButton)
        .whileTrue(new ClawControl(claw, -1));

    // Close Claw
    new JoystickButton(driverStation, DriverStationConstants.ClawCloseButton)
        .whileTrue(new ClawControl(claw, 1));

    // top position
    new JoystickButton(driverStation, 4).onTrue(armPlaceTopCommand);

    // middle position
    new JoystickButton(driverStation, 2).onTrue(armPlaceMidCommand);

    // Low position
    new JoystickButton(driverStation, 1).onTrue(armPlaceLowCommand);

    // default position
    new JoystickButton(driverStation, 7).onTrue(armdefaultCommand);

    // reset gyro
    new JoystickButton(controller, 7).onTrue(new GyroReset(driveSubsystem));

    // cancel automation
    new JoystickButton(driverStation, 8)
        .onTrue(
            new CancelArmAutomation(
                armMotors,
                led,
                armPlaceTopCommand,
                armPlaceMidCommand,
                armPlaceLowCommand,
                armdefaultCommand,
                armLoadCmd));

    // Loading position arm
    new JoystickButton(driverStation, 3).onTrue(armLoadCmd);

    // Balance testing
    new JoystickButton(controller, 3).onTrue(ConeAlign);

    new JoystickButton(controller, 4).onTrue(CubeAlign);

    new JoystickButton(controller, 9).onTrue(GyroTurn);

    new JoystickButton(controller, 2).toggleOnTrue(new LimelightLEDControl(vision));

    new JoystickButton(controller, 11)
        .onTrue(
            new CancelDriveAutomation(
                driveSubsystem, vision, gyroTurn, ConeAlign, CubeAlign, Balance, GyroTurn));

    // new JoystickButton(controller, 5).onTrue(Balance);
  }

  public Command getAutonomousCommand() {
    // switch(cycle){
    //   case 1:
    //     new Leave(driveSubsystem);
    //   case 2:
    //     new ScoreFloorLeave(armMotors, driveSubsystem, claw);
    //   default:
    //     break;
    // }
    // return null;
    return new ScoreTopLeaveClose(driveSubsystem, armMotors, claw);
  }
}
