package com.satyrlabs.scratchandcash;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(MainActivity activity);

    void inject(ScratchCardActivity activity);

    SharedPreferencesManager providesSharedPreferencesManager();
}
