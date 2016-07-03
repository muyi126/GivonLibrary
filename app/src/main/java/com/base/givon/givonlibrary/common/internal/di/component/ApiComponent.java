package com.base.givon.givonlibrary.common.internal.di.component;

//import org.estgroup.phphub.common.internal.di.module.ApiModule;
//import org.estgroup.phphub.common.service.NotificationService;
//import org.estgroup.phphub.ui.presenter.EditUserProfilePresenter;
//import org.estgroup.phphub.ui.presenter.RecommendedPresenter;
//import org.estgroup.phphub.ui.presenter.TopicDetailPresenter;
//import org.estgroup.phphub.ui.presenter.TopicPresenter;
//import org.estgroup.phphub.ui.presenter.TopicPublishPresenter;
//import org.estgroup.phphub.ui.presenter.TopicReplyPresenter;
//import org.estgroup.phphub.ui.presenter.UserNotificationsPresenter;
//import org.estgroup.phphub.ui.presenter.UserSpacePresenter;
//import org.estgroup.phphub.ui.presenter.UserTopicsPresenter;
//import org.estgroup.phphub.ui.presenter.WikiPresenter;
//import org.estgroup.phphub.ui.view.LoginActivity;

import com.base.givon.givonlibrary.common.internal.di.module.ApiModule;
import com.base.givon.givonlibrary.ui.presenter.RepositoriesPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApiModule.class)
public interface ApiComponent {
        void inject(RepositoriesPresenter repositoriesPresenter);
//
//        void inject(RecommendedPresenter recommendedPresenter);
//
//        void inject(WikiPresenter wikiPresenter);
//
//        void inject(TopicDetailPresenter topicDetailPresenter);
//
//        void inject(UserSpacePresenter userSpacePresenter);
//
//        void inject(LoginActivity loginActivity);
//
//        void inject(TopicPublishPresenter publishTopicPresenter);
//
//        void inject(TopicReplyPresenter topicReplyPresenter);
//
//        void inject(UserTopicsPresenter userTopicsPresenter);
//
//        void inject(EditUserProfilePresenter editUserProfilePresenter);
//
//        void inject(NotificationService notificationService);
//
//        void inject(UserNotificationsPresenter userNotificationsPresenter);
}