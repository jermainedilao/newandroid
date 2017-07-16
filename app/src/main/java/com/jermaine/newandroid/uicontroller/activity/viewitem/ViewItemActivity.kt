package com.jermaine.newandroid.uicontroller.activity.viewitem


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.jermaine.newandroid.R
import com.jermaine.newandroid.datamodel.repo.Repo
import com.jermaine.newandroid.uicontroller.activity.base.BaseActivity
import kotlinx.android.synthetic.main.item_layout.*

class ViewItemActivity : BaseActivity(), ViewItemContract.ViewItemView {

    companion object {
        val EXTRA_REPO_JSON: String = "extra:repo_json"
    }

    private var viewModel: ViewItemViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_item)

        viewModel = ViewModelProviders.of(this).get(ViewItemViewModel::class.java)
        viewModel?.init(component)

        val repo: Repo = viewModel?.getRepoFromJson(intent.extras.getString(EXTRA_REPO_JSON) ?: "{}")!!
        with(repo) {
            setId(id)
            setName(name)
            setDescription(description)
        }
    }

    override fun setId(id: String?) {
        idTextView.text = id
    }

    override fun setName(name: String?) {
        nameTextView.text = name
    }

    override fun setDescription(description: String?) {
        descriptionTextView.text = description
    }
}
