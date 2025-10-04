# This is generated automatically by the Android Gradle plugin.
-dontwarn java.lang.management.ManagementFactory
-dontwarn java.lang.management.RuntimeMXBean
-dontwarn org.slf4j.impl.StaticLoggerBinder
-dontwarn org.slf4j.impl.StaticMDCBinder

# General Android rules
-keepattributes Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-keep public class * extends java.lang.Exception

# Supabase specific rules
-keep class io.github.jan.supabase.** { *; }
-keep class org.postgresql.** { *; }
-keep class com.github.jasync.** { *; }
-keep class io.ktor.** { *; }
-keep class kotlinx.serialization.** { *; }
-keep class kotlinx.coroutines.** { *; }

# Keep serializable classes
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Keep your model classes
-keep class com.arsenal.sync.features.auth.data.dto.** { *; }
-keep class com.arsenal.sync.features.diet_plan.data.model.** { *; }
-keep class com.arsenal.sync.features.face_exe.data.model.** { *; }
-keep class com.arsenal.sync.features.gym_train.data.model.** { *; }
-keep class com.arsenal.sync.features.history.data.model.** { *; }
-keep class com.arsenal.sync.features.home.data.model.** { *; }


# Kotlin serialization
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt
-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}