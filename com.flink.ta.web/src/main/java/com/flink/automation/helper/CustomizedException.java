package com.flink.automation.helper;

public class CustomizedException extends RuntimeException{
	
	/** serialVersionUID. */
	private static final long serialVersionUID = -6423616719485855609L;

	/** Custom message. */
	private String message;

	/** Throwable cause for exception. */
	private Throwable cause;

	/**
	 * Constructor with custom message only.
	 *
	 * @param message Custom message
	 */
	public CustomizedException(final String message) {
		super(message);
		this.message = message;
	}

	/**
	 * Constructor with custom message and actual cause.
	 *
	 * @param message Custom message
	 * @param cause Exception cause
	 */
	public CustomizedException(final String message, final Throwable cause) {
		super(message, cause);
		this.message = message;
		this.cause = cause;
	}

	/**
	 * Returns custom message with cause.
	 *
	 * @return custom message with cause
	 */
	@Override
	public String getMessage() {
		if (cause != null) {
			this.message = cause.getMessage();
		}
		return this.message;
	}

}
