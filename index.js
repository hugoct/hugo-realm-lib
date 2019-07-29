import { NativeModules } from "react-native";

export const insert = (obj, callback) => {
  if (callback && callback.call && callback.apply) {
    NativeModules.libRealm.insert(JSON.stringify(obj), (res, error) =>
      callback(JSON.parse(res), JSON.parse(error))
    );
  }
};

export const insertMany = (objs, callback) => {
  if (callback && callback.call && callback.apply) {
    NativeModules.libRealm.insertMany(JSON.stringify(objs), (res, error) =>
      callback(JSON.parse(res), JSON.parse(error))
    );
  }
};

export const insertOrUpdateMany = (objs, callback) => {
  if (callback && callback.call && callback.apply) {
    NativeModules.libRealm.insertOrUpdateMany(
      JSON.stringify(objs),
      (res, error) => callback(JSON.parse(res), JSON.parse(error))
    );
  }
};

export const update = (obj, callback) => {
  if (callback && callback.call && callback.apply) {
    NativeModules.libRealm.update(JSON.stringify(obj), (res, error) =>
      callback(JSON.parse(res), JSON.parse(error))
    );
  }
};

export const deleteOne = (id, callback) => {
  if (callback && callback.call && callback.apply) {
    NativeModules.libRealm.delete(id, (res, error) =>
      callback(JSON.parse(res), JSON.parse(error))
    );
  }
};

export const deleteMany = (ids, callback) => {
  if (callback && callback.call && callback.apply) {
    NativeModules.libRealm.deleteMany(JSON.stringify(ids), (res, error) =>
      callback(JSON.parse(res), JSON.parse(error))
    );
  }
};

export const select = callback => {
  if (callback && callback.call && callback.apply) {
    NativeModules.libRealm.select((res, error) =>
      callback(JSON.parse(res), JSON.parse(error))
    );
  }
};

export default {
  select,
  insert,
  insertMany,
  update,
  insertOrUpdateMany,
  deleteOne,
  deleteMany
};
