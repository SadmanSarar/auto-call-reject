
# Automatic call reject application

This is a project is a part of Research into weather android os allows app to automatically reject all incoming call.

*Work In Progress* 
----------------------------------
### Permissions

```xml
<manifest>
    <uses-permission android:name="android.permission.CALL_PHONE"/>
    <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
    <uses-permission android:name="android.permission.PROCESS_INCOMING_CALLS"/>
    <uses-permission android:name="android.permission.PROCESS_OUTGOING_CALLS"/>
</manifest>
```

### Broadcast Receiver
This app registers a broadcast receiver for listening to all incoming calls in order to take actions against each call.

```kotlin
 override fun onReceive(context: Context?, intent: Intent?) {
        val tm = context?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        try {
            val c = Class.forName(tm.javaClass.name)
            val m = c.getDeclaredMethod("getITelephony")
            m.isAccessible = true
            val telephonyService = m.invoke(tm) as ITelephony
            val bundle = intent?.extras
            val phoneNumber = bundle?.getString("incoming_number")
            Log.d("INCOMING", phoneNumber)
            if (phoneNumber != null) {
                telephonyService.endCall()
                Log.d("HANG UP", phoneNumber)
                Log.d("INCOMING", "hang up $phoneNumber")
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
```  

There is a class `ITelephony` which is imported from the package `com.android.internal.telephony`

The project have `ITelephony` under `com.android.internal.telephony` package. The definition is provided below.

```java
public interface ITelephony {
    boolean endCall();
    void answerRingingCall();
    void silenceRinger();
}
``` 

This app uses a cool library named [`Dexter`](https://github.com/Karumi/Dexter) for handling all permission related stuff.

If you wanna test this project, just clone the repo and run the app in devices.