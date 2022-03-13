package com.mama.ai;

import java.util.concurrent.TimeUnit;

public class MainPresenter implements MainContract.Presenter{
    MainContract.View view;
    MainModel model = new MainModel();
    @Override
    public void setView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        view = null;
    }

    @Override
    public void setEPConTime(long epConTime) {
        model.setEpcon(epConTime);
    }

    @Override
    public void setEPCoffTime(long epCoffTime) {
        model.setEpcoff(epCoffTime);
    }

    @Override
    public void calculate() {
        long durationSec = Math.abs(model.getEpcon()- model.getEpcoff());
		
        long min = TimeUnit.MILLISECONDS.toMinutes(durationSec) % 60;
        int hour= (int) (durationSec/3600000L);
        String duration = hour+":"+min;
        view.setDuration(duration);
    }

}
