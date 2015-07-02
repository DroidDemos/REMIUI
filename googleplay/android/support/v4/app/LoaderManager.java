package android.support.v4.app;

import android.os.Bundle;
import android.support.v4.content.Loader;

public abstract class LoaderManager {

    public interface LoaderCallbacks<D> {
        Loader<D> onCreateLoader(int i, Bundle bundle);

        void onLoadFinished(Loader<D> loader, D d);

        void onLoaderReset(Loader<D> loader);
    }

    public abstract void destroyLoader(int i);

    public abstract <D> Loader<D> getLoader(int i);

    public abstract <D> Loader<D> initLoader(int i, Bundle bundle, LoaderCallbacks<D> loaderCallbacks);

    public abstract <D> Loader<D> restartLoader(int i, Bundle bundle, LoaderCallbacks<D> loaderCallbacks);

    public boolean hasRunningLoaders() {
        return false;
    }
}
