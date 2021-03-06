/*
 * Copyright 2015 The ISTLab. Use of this source code is governed by a GNU AFFERO GPL 3.0 license
 * that can be found in the LICENSE file.
 */

package gr.aueb.dmst.istlab.unixtools.dal.impl;

import gr.aueb.dmst.istlab.unixtools.core.model.CustomCommandModel;
import gr.aueb.dmst.istlab.unixtools.dal.CustomCommandRepository;
import gr.aueb.dmst.istlab.unixtools.dal.DataAccessException;
import gr.aueb.dmst.istlab.unixtools.io.IOStreamProvider;
import gr.aueb.dmst.istlab.unixtools.serialization.SerializationException;
import gr.aueb.dmst.istlab.unixtools.serialization.Serializer;
import gr.aueb.dmst.istlab.unixtools.serialization.StreamSerializer;

public final class CustomCommandRepositoryImpl implements CustomCommandRepository {

  private StreamSerializer<CustomCommandModel> streamSerializer;

  public CustomCommandRepositoryImpl(Serializer<CustomCommandModel> serializer,
      IOStreamProvider streamProvider) {
    this.streamSerializer = new StreamSerializer<>(serializer, streamProvider);
  }

  @Override
  public CustomCommandModel getModel() throws DataAccessException {
    try {
      return this.streamSerializer.deserialize();
    } catch (SerializationException ex) {
      throw new DataAccessException(ex);
    }
  }

  @Override
  public void saveModel(CustomCommandModel model) throws DataAccessException {
    try {
      this.streamSerializer.serialize(model);
    } catch (SerializationException ex) {
      throw new DataAccessException(ex);
    }
  }

}
