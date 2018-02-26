package com.kasztelanic.usernotes.utils;

import java.util.UUID;

public class UuidGenerator {

	public static String generate() {
		return UUID.randomUUID().toString();
	}
}
