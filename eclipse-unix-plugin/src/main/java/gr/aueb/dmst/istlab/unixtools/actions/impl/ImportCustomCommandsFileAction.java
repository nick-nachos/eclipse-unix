/*
 * Copyright 2015 The ISTLab. Use of this source code is governed by a GNU AFFERO GPL 3.0 license
 * that can be found in the LICENSE file.
 */

package gr.aueb.dmst.istlab.unixtools.actions.impl;

import gr.aueb.dmst.istlab.unixtools.actions.Action;
import gr.aueb.dmst.istlab.unixtools.actions.ActionExecutionCallback;
import gr.aueb.dmst.istlab.unixtools.actions.VoidActionResult;
import gr.aueb.dmst.istlab.unixtools.core.model.CustomCommandModel;
import gr.aueb.dmst.istlab.unixtools.importExport.CustomCommandImportExportHandler;
import gr.aueb.dmst.istlab.unixtools.importExport.ImportExportException;
import gr.aueb.dmst.istlab.unixtools.util.Logger;

public final class ImportCustomCommandsFileAction implements Action<VoidActionResult> {

  private CustomCommandModel model;
  private CustomCommandImportExportHandler importExportHandler;

  public ImportCustomCommandsFileAction(CustomCommandImportExportHandler importExportHandler,
      CustomCommandModel model) {
    this.importExportHandler = importExportHandler;
    this.model = model;
  }

  @Override
  public void execute(ActionExecutionCallback<VoidActionResult> callback) {
    VoidActionResult result = new VoidActionResult();

    try {
      CustomCommandModel importedModel = this.importExportHandler.importModel();
      this.model.setCommands(importedModel.getCommands());
    } catch (ImportExportException ex) {
      Logger logger = Logger.request();
      logger.log("Problem occured while executing the ImportCustomCommandsFileAction");
      result = new VoidActionResult(ex);
    }

    callback.onCommandExecuted(result);
  }

}
