package com.android.mvcdandagger.common.composition

import android.app.Activity
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.android.mvcdandagger.screens.common.dialogs.DialogsNavigator
import com.android.mvcdandagger.screens.common.navigator.ScreenNavigator
import com.android.mvcdandagger.screens.common.viewmvc.ViewMvcFactory
import com.android.mvcdandagger.screens.questiondetails.FetchDetailQuestionsUseCase
import com.android.mvcdandagger.screens.questionslist.FetchQuestionsUseCase

class ActivityCompositionRoot(
    private val activity: AppCompatActivity,
    private val appCompositionRoot: AppCompositionRoot
) {

    val screenNavigator by lazy {
        ScreenNavigator(activity)
    }

    private val layoutInflater get() = LayoutInflater.from(activity) //todo 5
    val viewMvcFactory get() = ViewMvcFactory(layoutInflater) //todo 6 (finish)

    private val fragmentManager get() = activity.supportFragmentManager
    val dialogNavigator get() = DialogsNavigator(fragmentManager)

    private val stackoverflowApi get() = appCompositionRoot.stackoverflowApi

    val fetchQuestionsUseCase get() = FetchQuestionsUseCase(stackoverflowApi)
    val fetchDetailQuestionsUseCase get() = FetchDetailQuestionsUseCase(stackoverflowApi)
}