package com.google.android.finsky.services;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.appstate.PackageStateRepository;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.services.IPlayGearheadService.Stub;

public class PlayGearheadService extends Service {
    private PlayGearheadServiceImpl mPlayGearheadServiceImpl;

    private class PlayGearheadServiceImpl extends Stub {
        private final Libraries mLibraries;
        private final PackageStateRepository mPackageStateRepository;

        public PlayGearheadServiceImpl(Libraries libraries, PackageStateRepository packageStateRepository) {
            this.mLibraries = libraries;
            this.mPackageStateRepository = packageStateRepository;
        }

        public Bundle validatePackageAcquiredByPlay(String packageName) throws RemoteException {
            boolean z = false;
            Bundle result = new Bundle();
            PackageState packageState = this.mPackageStateRepository.get(packageName);
            if (packageState == null) {
                result.putBoolean("Finsky.IsValid", false);
            } else {
                this.mLibraries.blockingLoad();
                String str = "Finsky.IsValid";
                if (this.mLibraries.getAppOwners(packageState.packageName, packageState.certificateHashes).size() > 0) {
                    z = true;
                }
                result.putBoolean(str, z);
            }
            return result;
        }
    }

    public void onCreate() {
        super.onCreate();
        this.mPlayGearheadServiceImpl = new PlayGearheadServiceImpl(FinskyApp.get().getLibraries(), FinskyApp.get().getPackageInfoRepository());
    }

    public IBinder onBind(Intent intent) {
        return this.mPlayGearheadServiceImpl;
    }
}
