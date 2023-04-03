// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups.AutoCmds;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Drivetrain.MecanumDriveCmd;
import frc.robot.subsystems.DrivetrainSubsystem;

public class LeaveFar extends SequentialCommandGroup {
  public LeaveFar(DrivetrainSubsystem driveTrain) {
    addCommands(
<<<<<<< HEAD
      new MecanumDriveCmd(driveTrain, () -> 0.0, () -> 0.4, () -> 0.0, () -> 3.0, () -> false)
        .withTimeout(2.5),//1.3
      new WaitCommand(1));









=======
        new MecanumDriveCmd(driveTrain, () -> 0.0, () -> 0.4, () -> 0.0, () -> 3.0)
            .withTimeout(2.5), // 1.3
        new WaitCommand(1));
>>>>>>> 72f988e6684c9724fec07effd382f7f934e4e1b1
  }
}
