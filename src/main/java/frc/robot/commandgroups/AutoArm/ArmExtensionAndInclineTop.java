// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups.AutoArm;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Arm.ArmExtend;
import frc.robot.commands.Arm.ArmExtendTop;
import frc.robot.commands.Arm.ArmInclineTop;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ArmExtensionAndInclineTop extends SequentialCommandGroup {
  /** Creates a new ArmExtensionAndInclineAuto. */
  public ArmExtensionAndInclineTop(
      Arm arm, Claw claw, double angleSetpoint, double extensionSetpoint) {
    // addCommands(
    //     new ArmInclineTop(arm, angleSetpoint),
    //     new WaitCommand(0.5),
    //     new ArmExtendTop(arm, extensionSetpoint)
    //         .andThen(
    //             new ArmExtend(arm, () -> 0.15)
    //                 .alongWith(
    //                     new WaitCommand(0.5).andThen(new ArmInclineTop(arm, angleSetpoint -
    // 5)))));

    addCommands(
        new ArmInclineTop(arm, angleSetpoint),
        new WaitCommand(0.5),
        new ArmExtendTop(arm, extensionSetpoint),
        new WaitCommand(0.5).raceWith(new ArmExtend(arm, () -> -0.20)));
    // new ArmExtend(arm, () -> -0.2).raceWith(new ClawControl(claw, -1)).withTimeout(0.3));
  }
}
