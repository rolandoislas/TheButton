package com.rolandoislas.thebutton.playservices;

/**
 * Created by Rolando on 5/15/2016.
 */
public interface PlayServices {
    // Game
    void signIn();
    void signOut();
    void rateGame();
    void unlockAchievement(String name);
    void submitScore(long score);
    void showAchievement();
    void showScores();
    boolean isSignedIn();
    // Ad
    void showBannerAd();
    void hideBannerAd();
    // Intent
	void openBrowser(String url);
}
