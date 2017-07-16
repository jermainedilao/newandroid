package com.jermaine.newandroid.uicontroller.activity.viewitem


import com.jermaine.newandroid.base.BaseView

interface ViewItemContract {
    interface ViewItemView : BaseView {
        fun setId(id: String?)

        fun setName(name: String?)

        fun setDescription(description: String?)
    }
}
