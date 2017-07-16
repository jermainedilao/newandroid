package com.jermaine.newandroid.uicontroller.activity.additem


import com.jermaine.newandroid.base.BaseView

interface AddRepoContract {
    interface AddRepoView : BaseView {
        val id: String

        val name: String

        val description: String

        fun setIdError()

        fun setNameError()

        fun setDescriptionError()

        fun finishScreen()
    }
}
