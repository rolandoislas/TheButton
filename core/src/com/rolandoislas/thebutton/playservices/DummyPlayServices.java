package com.rolandoislas.thebutton.playservices;

/**
 * Created by Rolando on 5/15/2016.
 */
public class DummyPlayServices implements PlayServices {
    @Override
    public void signIn() {

    }

    @Override
    public void signOut() {

    }

    @Override
    public void rateGame() {

    }

    @Override
    public void unlockAchievement(String name) {

    }

    @Override
    public void submitScore(long score) {

    }

    @Override
    public void showAchievement() {

    }

    @Override
    public void showScores() {

    }

    @Override
    public boolean isSignedIn() {
        return false;
    }

    @Override
    public void showBannerAd() {

    }

    @Override
    public void hideBannerAd() {

    }
}
