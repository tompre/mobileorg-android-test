package com.matburt.mobileorg.test.Synchronizers;

import android.content.Context;

import com.matburt.mobileorg.Synchronizers.SynchronizerNotification;

public class SynchronizerNotificationStub extends SynchronizerNotification {

	public SynchronizerNotificationStub(Context context) {
		super(context);
	}

	@Override
	public void errorNotification(String errorMsg) {
	}

	@Override
	public void setupNotification() {
	}

	@Override
	public void updateNotification(int progress) {
	}

	@Override
	public void updateNotification(int progress, String message) {
	}

	@Override
	public void updateNotification(int fileNumber, String message,
			int totalFiles) {
	}

	@Override
	public void finalizeNotification() {
	}

	
}
