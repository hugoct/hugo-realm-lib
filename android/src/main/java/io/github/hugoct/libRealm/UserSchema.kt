package io.github.hugoct.libRealm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

// Your model has to extend RealmObject. Furthermore, the class must be annotated with open (Kotlin classes are final
// by default).
open class UserSchema(
        // You can put properties in the constructor as long as all of them are initialized with
        // default values. This ensures that an empty constructor is generated.
        // All properties are by default persisted.
        // Properties can be annotated with PrimaryKey or Index.
        // If you use non-nullable types, properties must be initialized with non-null values.
        @PrimaryKey var id: Long = 0,

        var name: String = "",

        var email: String = "",

        var address: String = ""

) : RealmObject() {
    // The Kotlin compiler generates standard getters and setters.
    // Realm will overload them and code inside them is ignored.
    // So if you prefer you can also just have empty abstract methods.
}