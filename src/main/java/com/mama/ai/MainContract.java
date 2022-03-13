package com.mama.ai;

/**
 * Special edition for my MaMa
 * @author Pyae Phyo Kyaw*/
public interface MainContract {
    interface Presenter{
        void setView(MainContract.View view);
        void dropView();
        void setEPConTime(long epConTime);
        void setEPCoffTime(long epCoffTime);
        void calculate();
    }
    interface View{
        void setDuration(String duration);
    }
}
