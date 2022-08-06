package com.idsr.mobile.models.APIModels;

import com.idsr.mobile.models.Event;

public class EventJs {
	private Event event;

	public EventJs(Event event) {
		this.event = event;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
}
