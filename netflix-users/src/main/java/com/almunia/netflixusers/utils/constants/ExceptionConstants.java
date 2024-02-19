package com.almunia.netflixusers.utils.constants;

public class ExceptionConstants {

	public static final String ERROR = "ERROR";

	public static final String MESSAGE_INEXISTENT_SEASON = "SEASON INEXISTENT - Season does not exist";

	public static final String MESSAGE_CREATION_ERROR = "CREATION ERROR - Error creating the object";

	public static final String MESSAGE_INEXISTENT_CHAPTER = "CHAPTER INEXISTENT - Chapter does not exist";

	public static final String MESSAGE_INEXISTENT_AWARDS = "AWARDS INEXISTENT - There are no awards";

	public static final String MESSAGE_CATEGORY_ALREADY_EXISTS = "CATEGORY ALREADY EXISTS - Category already exists";
	public static final String MESSAGE_ACTOR_ALREADY_EXISTS = "ACTOR ALREADT EXISTS - Actor already exsits";

	public static final String MESSAGE_INEXISTENT_TVSHOW = "TVSHOW INEXISTENT - TvShow does not exist";

	public static final String MESSAGE_INEXISTENT_CATEGORY = "CATEGORY INEXISTENT - Category does not exist";

	public static final String MESSAGE_INEXISTENT_ACTOR = "ACTOR INEXISTENT - Actor does not exist";

	public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR - An internal server error has ocurred";

	private ExceptionConstants() {
		throw new IllegalStateException("Utility Class");
	}

}
