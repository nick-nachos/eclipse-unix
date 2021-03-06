/*
 * Copyright 2015 The ISTLab. Use of this source code is governed by a GNU AFFERO GPL 3.0 license
 * that can be found in the LICENSE file.
 */

package gr.aueb.dmst.istlab.unixtools.actions.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import gr.aueb.dmst.istlab.unixtools.actions.Action;
import gr.aueb.dmst.istlab.unixtools.actions.ActionExecutionCallback;
import gr.aueb.dmst.istlab.unixtools.actions.DataActionResult;
import gr.aueb.dmst.istlab.unixtools.core.model.CustomCommand;
import gr.aueb.dmst.istlab.unixtools.util.SystemInfo;

public final class ExecuteCustomCommandAction implements Action<DataActionResult<InputStream>> {

  private CustomCommand commandToExecute;

  public ExecuteCustomCommandAction(CustomCommand commandToExecute) {
    this.commandToExecute = commandToExecute;
  }


  @Override
  public void execute(ActionExecutionCallback<DataActionResult<InputStream>> callback) {
    DataActionResult<InputStream> result;
    List<String> arguments = SystemInfo.getSystemShellInfo();
    arguments.add(this.commandToExecute.getCommand());

    ProcessBuilder pb = new ProcessBuilder(arguments);
    pb.redirectErrorStream(true);
    pb.directory(null);

    Process p;
    try {
      p = pb.start();
      p.waitFor();
      InputStream cmdStream = p.getInputStream();
      result = new DataActionResult<>(cmdStream);
    } catch (IOException ex) {
      result = new DataActionResult<>(ex);
    } catch (InterruptedException ex) {
      result = new DataActionResult<>(ex);
    }

    callback.onCommandExecuted(result);
  }

}
