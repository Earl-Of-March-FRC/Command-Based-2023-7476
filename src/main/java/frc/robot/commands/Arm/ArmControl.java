// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Arm;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.Arm;
import java.util.function.DoubleSupplier;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ArmControl extends ParallelCommandGroup {
  public ArmControl(Arm armMotors, DoubleSupplier extendSpeed, DoubleSupplier inclineSpeed) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new ArmIncline(armMotors, inclineSpeed), new ArmExtend(armMotors, extendSpeed));
    addRequirements(armMotors);
    // double inclineSpeed2 = inclineSpeed.getAsDouble();
    // double horizDistance=Math.cos();
    // if(inclineSpeed2<0){

    // }

    // if(armMotors.getInclineAngle() < 30 ){
    //   addCommands(new ArmRetract(armMotors, 50));
    // }
  }
}
