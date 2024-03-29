// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.VisionSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class CancelDriveAutomation extends InstantCommand {
  DriveTrainSubsystem drive;
  VisionSubsystem vision;

  public Command gyroTurn, coneAlign, cubeAlign, balance, turn;

  public CancelDriveAutomation(
      DriveTrainSubsystem drive,
      VisionSubsystem vision,
      Command gyroTurn,
      Command coneAlign,
      Command cubeAlign,
      Command Balance,
      Command GyroTurn) {

    this.gyroTurn = gyroTurn;
    this.drive = drive;
    this.coneAlign = coneAlign;
    this.cubeAlign = cubeAlign;
    this.vision = vision;
    this.balance = Balance;
    turn = GyroTurn;

    addRequirements(drive, vision);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    gyroTurn.cancel();
    coneAlign.cancel();
    cubeAlign.cancel();
    vision.limeLEDOff();
    balance.cancel();
    turn.cancel();
  }
}
