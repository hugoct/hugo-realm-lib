import { NativeModules } from "react-native";

const insert = (obj, callback) => {
  NativeModules.libRealm.insert(JSON.stringify(obj), (res, error) =>
    callback(JSON.parse(res), JSON.parse(error))
  );
};

const insertMany = (objs, callback) => {
  NativeModules.libRealm.insertMany(JSON.stringify(objs), (res, error) =>
    callback(JSON.parse(res), JSON.parse(error))
  );
};

const insertOrUpdateMany = (objs, callback) => {
  NativeModules.libRealm.insertOrUpdateMany(
    JSON.stringify(objs),
    (res, error) => callback(JSON.parse(res), JSON.parse(error))
  );
};

const update = (obj, callback) => {
  NativeModules.libRealm.update(JSON.stringify(obj), (res, error) =>
    callback(JSON.parse(res), JSON.parse(error))
  );
};

const deleteOne = (id, callback) => {
  NativeModules.libRealm.delete(id, (res, error) =>
    callback(JSON.parse(res), JSON.parse(error))
  );
};

const deleteMany = (ids, callback) => {
  NativeModules.libRealm.deleteMany(JSON.stringify(ids), (res, error) =>
    callback(JSON.parse(res), JSON.parse(error))
  );
};

const select = callback => {
  NativeModules.libRealm.select((res, error) =>
    callback(JSON.parse(res), JSON.parse(error))
  );
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
