# hugo-realm-lib

hugo-realm-lib is a lib for React Native that provides a simple way to use Realm Database

## Installation

```bash
yarn add hugo-realm-lib

react-native link
```

Make sure to add the Realm dependency in your build.gradle file on project level

```javascript
dependencies {
    classpath "io.realm:realm-gradle-plugin:5.13.0"
}
```

## Usage

```javascript
import {
  select,
  insert,
  insertMany,
  insertOrUpdateMany,
  deleteOne,
  deleteMany,
  update
} from "hugo-realm-lib";

select((items, error) => {
  // do something
});

insert(obj, (createdObj, error) => {
  // do something
});

insertMany([obj1, obj2], (createdObjectsList, error) => {
  // do something
});

insertOrUpdateMany([obj1, obj2], (createdOrUpdatedObjectsList, error) => {
  // do something
});

deleteOne(id, (successBoolean, error) => {
  // do something
});

deleteMany([id1, id2], (createdOrUpdatedObjectsList, error) => {
  // do something
});

update(obj, (updatedObj, error) => {
  // do something
});
```

## License

[MIT](https://choosealicense.com/licenses/mit/)
