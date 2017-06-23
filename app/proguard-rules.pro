# common configurations
-dontobfuscate
-dontoptimize
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-repackageclasses ''
-allowaccessmodification
-optimizations !code/simplification/arithmetic
-keepattributes *Annotation*

# android configurations
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider

-keep public class * extends android.view.View {
      public <init>(android.content.Context);
      public <init>(android.content.Context, android.util.AttributeSet);
      public <init>(android.content.Context, android.util.AttributeSet, int);
      public void set*(...);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * extends android.content.Context {
    public void *(android.view.View);
    public void *(android.view.MenuItem);
}
-keepclassmembers class * implements android.os.Parcelable {
    static ** CREATOR;
}
-keepclassmembers class **.R$* {
    public static <fields>;
}
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

# sugarorm and guava configurations --
-dontwarn com.google.common.base.**
-keep class com.google.common.base.** {*;}
-dontwarn com.google.errorprone.annotations.**
-keep class com.google.errorprone.annotations.** {*;}
-dontwarn   com.google.j2objc.annotations.**
-keep class com.google.j2objc.annotations.** { *; }
-dontwarn   java.lang.ClassValue
-keep class java.lang.ClassValue { *; }
-dontwarn   org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-keep class org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement { *; }
-dontwarn com.google.errorprone.annotations.CanIgnoreReturnValue
-dontwarn com.google.errorprone.annotations.concurrent.LazyInit
-dontwarn com.google.errorprone.annotations.ForOverride
-dontwarn javax.annotation.**
-dontwarn javax.inject.**
-dontwarn sun.misc.Unsafe

# feather configurations
-keep public class org.codejargon.feather.**
-keep public class com.dariuszdeoniziak.charades.**
-keep class org.codejargon.feather.**
-keep class com.dariuszdeoniziak.charades.**
-keepclassmembers class com.dariuszdeoniziak.charades.** {*;}
-keepclassmembers class org.codejargon.feather.** {*;}