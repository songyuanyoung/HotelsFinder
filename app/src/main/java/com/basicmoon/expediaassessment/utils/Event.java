package com.basicmoon.expediaassessment.utils;

/**
 * An event class that is passed from ViewModels to activities.
 * @param <T>
 */
public class Event<T> {

    private T mEventContent;

    public Event(T eventContent) {
        if (eventContent == null) {
            throw new IllegalArgumentException("null values in Event are not allowed.");
        }
        mEventContent = eventContent;
    }

    public T getEventContent() {
        return mEventContent;
    }
}
