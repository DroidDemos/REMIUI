package com.google.android.gms.people;

import com.google.android.gms.people.model.CircleBuffer;
import com.google.android.gms.people.model.PersonBuffer;

public interface Graph {

    public interface LoadPeopleResult extends People$ReleasableResult {
        PersonBuffer getPeople();
    }

    public interface LoadCirclesResult extends People$ReleasableResult {
        CircleBuffer getCircles();
    }
}
