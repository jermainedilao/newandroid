package com.jermaine.newandroid.uicontroller.activity.additem

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.jermaine.newandroid.R
import com.jermaine.newandroid.uicontroller.activity.base.BaseActivity
import kotlinx.android.synthetic.main.activity_add_repo.*

class AddRepoActivity : BaseActivity(), AddRepoContract.AddRepoView {

    private var viewModel: AddRepoViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_repo)

        viewModel = ViewModelProviders.of(this).get(AddRepoViewModel::class.java)
        viewModel?.init(component)
    }

    @Suppress("UNUSED_PARAMETER")
    fun onAddClick(view: View) {
        var flag = false
        if (id.isBlank()) {
            flag = true
            setIdError()
        }
        if (name.isBlank()) {
            flag = true
            setNameError()
        }
        if (description.isBlank()) {
            flag = true
            setDescriptionError()
        }

        if (flag) {
            return
        }

        viewModel?.addRepo(id, name, description)
        finishScreen()
    }

    override val id: String
        get() = idEditText.text.toString()

    override val name: String
        get() = nameEditText.text.toString()

    override val description: String
        get() = descriptionEditText.text.toString()

    override fun setIdError() {
        idEditText.error = getString(R.string.required)
    }

    override fun setNameError() {
        nameEditText.error = getString(R.string.required)
    }

    override fun setDescriptionError() {
        descriptionEditText.error = getString(R.string.required)
    }

    override fun finishScreen() {
        finish()
    }
}
