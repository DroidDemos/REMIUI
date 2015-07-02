package com.google.android.gms.plus.model.people;

import com.google.android.gms.common.data.Freezable;

public interface Person extends Freezable {

    public interface AgeRange extends Freezable {
    }

    public interface Cover extends Freezable {

        public interface CoverInfo extends Freezable {
        }

        public interface CoverPhoto extends Freezable {
        }
    }

    public interface Image extends Freezable {
    }

    public interface Name extends Freezable {
    }

    public interface Organizations extends Freezable {
    }

    public interface PlacesLived extends Freezable {
    }

    public interface Urls extends Freezable {
    }
}
