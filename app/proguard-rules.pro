# TestBinlist ProGuard/R8 Rules

# Preserve line numbers for debugging
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# --- Kotlinx Serialization ---
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt
-keepclassmembers class kotlinx.serialization.json.** { *** Companion; }
-keepclasseswithmembers class * {
    @kotlinx.serialization.Serializable <methods>;
}

# Keep serializable DTOs
-keep @kotlinx.serialization.Serializable class com.example.testbinlist.data.dto.** { *; }

# --- Ktor Client ---
-keep class io.ktor.client.** { *; }
-keep class io.ktor.http.** { *; }
-keep class io.ktor.util.** { *; }
-dontwarn io.ktor.**

# --- Room ---
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.paging.**

# --- Koin ---
-keep class org.koin.** { *; }
-dontwarn org.koin.**

# --- Compose (runtime) ---
-keep class androidx.compose.runtime.** { *; }

# --- General Kotlin ---
-keep class kotlin.reflect.jvm.internal.** { *; }
-dontwarn kotlin.reflect.jvm.internal.**
