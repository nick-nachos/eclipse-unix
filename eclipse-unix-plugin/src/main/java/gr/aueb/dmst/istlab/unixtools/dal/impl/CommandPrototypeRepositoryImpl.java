/*
 * Copyright 2015 The ISTLab. Use of this source code is governed by a GNU AFFERO GPL 3.0 license
 * that can be found in the LICENSE file.
 */

package gr.aueb.dmst.istlab.unixtools.dal.impl;

import gr.aueb.dmst.istlab.unixtools.core.model.CommandPrototypeModel;
import gr.aueb.dmst.istlab.unixtools.dal.CommandPrototypeRepository;
import gr.aueb.dmst.istlab.unixtools.dal.DataAccessException;
import gr.aueb.dmst.istlab.unixtools.io.IOStreamProvider;
import gr.aueb.dmst.istlab.unixtools.serialization.SerializationException;
import gr.aueb.dmst.istlab.unixtools.serialization.Serializer;
import gr.aueb.dmst.istlab.unixtools.serialization.StreamSerializer;

public final class CommandPrototypeRepositoryImpl implements CommandPrototypeRepository {

  private StreamSerializer<CommandPrototypeModel> streamSerializer;

  public CommandPrototypeRepositoryImpl(Serializer<CommandPrototypeModel> serializer,
      IOStreamProvider streamProvider) {
    this.streamSerializer = new StreamSerializer<>(serializer, streamProvider);
  }

  @Override
  public CommandPrototypeModel getModel() throws DataAccessException {
    try {
      return this.streamSerializer.deserialize();
    } catch (SerializationException ex) {
      throw new DataAccessException(ex);
    }
  }

  @Override
  public void saveModel(CommandPrototypeModel model) throws DataAccessException {
    try {
      this.streamSerializer.serialize(model);
    } catch (SerializationException ex) {
      throw new DataAccessException(ex);
    }
  }

}
