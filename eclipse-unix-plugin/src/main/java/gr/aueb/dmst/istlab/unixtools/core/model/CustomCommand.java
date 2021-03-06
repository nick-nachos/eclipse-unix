/*
 * Copyright 2015 The ISTLab. Use of this source code is governed by a GNU AFFERO GPL 3.0 license
 * that can be found in the LICENSE file.
 */

package gr.aueb.dmst.istlab.unixtools.core.model;

import java.beans.ConstructorProperties;

public final class CustomCommand {

  private String command;
  private String description;
  private String shellDirectory;
  private boolean outputToConsole;
  private String outputFilename;

  public CustomCommand() {}

  @ConstructorProperties({"command", "description", "shellDirectory", "outputToConsole",
      "outputFilename"})
  public CustomCommand(String command, String description, String shellDirectory,
      boolean outputToConsole, String outputFilename) {
    this.setCommand(command);
    this.setDescription(description);
    this.setShellDirectory(shellDirectory);
    this.setOutputToConsole(outputToConsole);
    this.setOutputFilename(outputFilename);
  }

  public String getCommand() {
    return command;
  }

  public void setCommand(String command) {
    this.command = command;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getShellDirectory() {
    return shellDirectory;
  }

  public void setShellDirectory(String shellDirectory) {
    this.shellDirectory = shellDirectory;
  }

  public boolean isOutputToConsole() {
    return outputToConsole;
  }

  public void setOutputToConsole(boolean outputToConsole) {
    this.outputToConsole = outputToConsole;
  }

  public String getOutputFilename() {
    return outputFilename;
  }

  public void setOutputFilename(String outputFilename) {
    this.outputFilename = outputFilename;
  }

}
