package com.example.asthmaapplication.main.common;

import androidx.lifecycle.Observer;

public class Event<T> {

    private T content;
    private boolean hasBeenHandled = false;

    public Event(T content) {
        this.content = content;
    }

    public T getContentIfNotHandled() {
        if (hasBeenHandled) {
            return null;
        } else {
            hasBeenHandled = true;
            return content;
        }
    }

    public T peekContent() {
        return content;
    }

    public static class EventObserver<T> implements Observer<Event<? extends T>> {


        private EventUnhandledContent<T> content;

        public EventObserver(EventUnhandledContent<T> content) {
            this.content = content;
        }

        @Override
        public void onChanged(Event<? extends T> event) {
            if (event != null) {
                T result = event.getContentIfNotHandled();
                if (result != null && content != null) {
                    content.onEventUnhandledContent(result);
                }
            }
        }
    }

    public interface EventUnhandledContent<T> {
        void onEventUnhandledContent(T t);
    }
}