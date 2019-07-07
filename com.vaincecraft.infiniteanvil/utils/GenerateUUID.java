package com.vaincecraft.infiniteanvil.utils;

import java.util.UUID;

public class GenerateUUID {
	private UUID uuid;
	public UUID generateUUID() {
		uuid = UUID.randomUUID();
		return uuid;
	}
}
