package com.goncharov.evgeny.obstacleavoid.ios;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;
import com.goncharov.evgeny.obstacleavoid.App;

/**
 * Launches the iOS (RoboVM) application.
 */
public class IOSApp extends IOSApplication.Delegate {
    @Override
    protected IOSApplication createApplication() {
        IOSApplicationConfiguration configuration = new IOSApplicationConfiguration();
        configuration.orientationPortrait = true;
        return new IOSApplication(new App(), configuration);
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSApp.class);
        pool.close();
    }
}