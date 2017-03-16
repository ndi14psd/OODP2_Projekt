package model;

import java.util.function.Consumer;

public final class Attribute {

	private final Consumer<String> updater;
	private final String name;
	private final String value;

	public Attribute(String currentValue, String name, Consumer<String> updater) {
		this.value = currentValue;
		this.name = name;
		this.updater = updater;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public Consumer<String> getUpdater() {
		return updater;
	}

}
