package com.rolandoislas.thebutton;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;
import com.rolandoislas.thebutton.playservices.PlayServices;

public class AndroidLauncher extends AndroidApplication implements PlayServices {

	private GameHelper gameHelper;
	private int requestCode = 1;
	private int attempts = 0;
	private AdView bannerAdView;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Play Services
		gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
		gameHelper.enableDebugLog(false);
		gameHelper.setMaxAutoSignInAttempts(0);
		final GameHelper.GameHelperListener gameHelperListener = new GameHelper.GameHelperListener() {
			@Override
			public void onSignInFailed() {
				if (attempts == 0) {
					attempts++;
					return;
				}
				Dialog errorDialog = gameHelper.makeSimpleDialog(
						getString(R.string.common_google_play_services_sign_in_failed_title),
						getString(R.string.common_google_play_services_sign_in_failed_text));
				errorDialog.show();
			}

			@Override
			public void onSignInSucceeded() {

			}
		};
		gameHelper.setup(gameHelperListener);
		// Configuration
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useImmersiveMode = true;
		config.useAccelerometer = false;
		config.useCompass = false;
		config.useGyroscope = false;
		View gameView = initializeForView(new TheButton(this), config);
		createAdView();
		// Define layout
		RelativeLayout layout = new RelativeLayout(this);
		layout.addView(gameView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		layout.addView(bannerAdView, params);
		setContentView(layout);
	}

	private void createAdView() {
		bannerAdView = new AdView(this);
		bannerAdView.setVisibility(View.INVISIBLE);
		bannerAdView.setBackgroundColor(Color.BLACK);
		bannerAdView.setAdUnitId(getString(R.string.post_screen_banner_ad_id));
		bannerAdView.setAdSize(AdSize.SMART_BANNER);
	}

	@Override
	protected void onStart() {
		super.onStart();
		gameHelper.onStart(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		gameHelper.onStop();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		gameHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void signIn() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				gameHelper.beginUserInitiatedSignIn();
			}
		});
	}

	@Override
	public void signOut() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				gameHelper.signOut();
			}
		});
	}

	@Override
	public void rateGame() {
		String url = "https://play.google.com/store/apps/details?id=com.rolandoislas.thebutton";
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
	}

	@Override
	public void unlockAchievement(String name) {
		if (isSignedIn())
        	Games.Achievements.unlock(gameHelper.getApiClient(), getString(getResources().getIdentifier(name, "string",
					getPackageName())));
    }

	@Override
	public void submitScore(long score) {
		if (isSignedIn())
			Games.Leaderboards.submitScore(gameHelper.getApiClient(), getString(R.string.leaderboard_score), score);
	}

	@Override
	public void showAchievement() {
		if (isSignedIn())
			startActivityForResult(Games.Achievements.getAchievementsIntent(gameHelper.getApiClient()), requestCode);
		else
			signIn();
	}

	@Override
	public void showScores() {
		if (isSignedIn())
			startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(),
					getString(R.string.leaderboard_score)), requestCode);
		else
			signIn();
	}

	@Override
	public boolean isSignedIn() {
		return gameHelper.isSignedIn();
	}

	@Override
	public void showBannerAd() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				bannerAdView.setVisibility(View.VISIBLE);
				AdRequest.Builder builder = new AdRequest.Builder();
				builder.addTestDevice("399A5A84DB0C8ECF8A14F464E92E6D38");
				builder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);
				AdRequest ad = builder.build();
				bannerAdView.loadAd(ad);
			}
		});
	}

	@Override
	public void hideBannerAd() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				bannerAdView.setVisibility(View.INVISIBLE);
			}
		});
	}
}
