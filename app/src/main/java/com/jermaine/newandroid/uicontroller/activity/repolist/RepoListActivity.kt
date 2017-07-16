package com.jermaine.newandroid.uicontroller.activity.repolist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.google.gson.Gson
import com.jermaine.newandroid.R
import com.jermaine.newandroid.datamodel.repo.Repo
import com.jermaine.newandroid.repository.callback.OnFetchReposCallback
import com.jermaine.newandroid.uicontroller.activity.additem.AddRepoActivity
import com.jermaine.newandroid.uicontroller.activity.base.BaseActivity
import com.jermaine.newandroid.uicontroller.activity.viewitem.ViewItemActivity
import com.jermaine.newandroid.uicontroller.adapter.repolist.RepoListAdapter
import com.jermaine.newandroid.uicontroller.adapter.repolist.callback.OnItemClickListener
import kotlinx.android.synthetic.main.activity_repo_list.*
import java.util.*
import javax.inject.Inject

class RepoListActivity : BaseActivity() {

    @Inject
    lateinit var mGson: Gson

    private var mAdapter: RepoListAdapter? = null

    private var viewModel: RepoListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)
        component.inject(this)

        viewModel = ViewModelProviders.of(this).get(RepoListViewModel::class.java)
        viewModel?.init(component)

        fetchRepos()
        initializeViews()
    }

    fun initializeViews() {
        mAdapter = RepoListAdapter(ArrayList<Repo>())

        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = mAdapter

        swipeRefreshLayout.setOnRefreshListener { fetchRepos() }

        setOnItemClickListener()
    }

    private fun fetchRepos() {
        showProgressDialog()
        viewModel?.fetchRepos(object : OnFetchReposCallback {
            override fun onSuccess(repoList: LiveData<List<Repo>>) {
                repoList.observe(this@RepoListActivity, android.arch.lifecycle.Observer {
                    provideRepos(it)
                })
            }

            override fun onError() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    private fun setOnItemClickListener() {
        mAdapter?.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                startViewItemScreen(mGson.toJson(mAdapter?.get(position)))
            }
        })
    }

    fun provideRepos(repoList: List<Repo>?) {
        dismissProgressDialog()
        mAdapter?.updateData(repoList)
    }

    fun showProgressDialog() {
        if (!swipeRefreshLayout.isRefreshing) {
            swipeRefreshLayout.isRefreshing = true
        }
    }

    fun dismissProgressDialog() {
        swipeRefreshLayout.isRefreshing = false
    }

    fun startViewItemScreen(repoJson: String) {
        val intent = Intent(this, ViewItemActivity::class.java)
        val extras = Bundle()
        extras.putString(ViewItemActivity.EXTRA_REPO_JSON, repoJson)
        intent.putExtras(extras)

        startActivity(intent)
    }

    @Suppress("UNUSED_PARAMETER")
    fun onAddClick(view: View) {
        val intent = Intent(this, AddRepoActivity::class.java)
        startActivity(intent)
    }
}