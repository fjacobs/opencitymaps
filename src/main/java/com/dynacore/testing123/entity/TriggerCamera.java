package com.dynacore.testing123.entity;
import java.util.UUID;

public class TriggerCamera {
	UUID id;
	boolean trafficJam;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public TriggerCamera(boolean trafficJam) {
		this.id = UUID.randomUUID();
		this.trafficJam = trafficJam;
	}

	public boolean isTrafficJam() {
		return trafficJam;
	}

	public void setTrafficJam(boolean trafficJam) {
		this.trafficJam = trafficJam;
	}
}

