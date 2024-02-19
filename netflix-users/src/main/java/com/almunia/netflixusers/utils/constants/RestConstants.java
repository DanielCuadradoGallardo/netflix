package com.almunia.netflixusers.utils.constants;

public class RestConstants {
	public static final String APPLICATION_NAME = "/netflix";

	public static final String SUCCESS = "Success";
	public static final String RESOURCE_CATEGORY = "/categories";
	public static final String RESOURCE_TV_SHOW = "/tv-shows";
	public static final String RESOURCE_ACTORS = "/actors";
	public static final String RESOURCE_SEASON = "/seasons";
	public static final String RESOURSE_SEASONS_BY_TVSHOW = "/tv-show/{tvShowId}";
	public static final String RESOURSE_SEASONS_BY_TVSHOW_AND_SEASON_NUMBER = "/tv-show/{tvShowId}/season/{SeasonNumber}";
	public static final String RESOURCE_TV_SHOW_CATEGORY = "/{tvShowId}/categories/{categoryId}";
	public static final String RESOURCE_CHAPTER = "/chapters/";
	public static final String RESOURCE_CHAPTER_BY_SEASON_AND_NUMBER ="/tv-show/{tvShowId}/season/{seasonNumber}";
	public static final String RESOURCE_UPDATE_CHAPTPER_NAME="/tv-show/{tvShowId}/season/{seasonNumber}/chapter/{chapterId}";
	public static final String AND_CHAPTER_NUMBER = "/chapter/{chapterNumber}";
	public static final String TVSHOW_ID = "/{tvShowId}";
	public static final String ACTOR_ID = "/{ActorId}";
	public static final String SEASON_ID = "/{SeasonId}";
	public static final String PARAMETER_CATEGORY = "categories";
    public static final String RESOURCE_AWARDS = "/awards";

    private RestConstants() {
		throw new IllegalStateException("Utility Class");
	}

}
