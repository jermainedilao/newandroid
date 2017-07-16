package com.jermaine.newandroid.uicontroller.adapter.repolist


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jermaine.newandroid.R
import com.jermaine.newandroid.datamodel.repo.Repo
import com.jermaine.newandroid.uicontroller.adapter.repolist.callback.OnItemClickListener
import com.jermaine.newandroid.uicontroller.adapter.repolist.viewholder.RepoViewHolder

class RepoListAdapter(repoList: List<Repo>) : RecyclerView.Adapter<RepoViewHolder>() {

    private var repoList: MutableList<Repo> = ArrayList()
    private var onItemClickListener: OnItemClickListener? = null

    init {
        updateData(repoList)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bindRepo(
                repoList[position],
                View.OnClickListener { onItemClickListener?.onItemClick(position) }
        )
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    operator fun get(position: Int): Repo? {
        return repoList[position]
    }

    fun updateData(repoList: List<Repo>?) {
        this.repoList.clear()
        this.repoList.addAll(repoList ?: ArrayList())
        notifyDataSetChanged()
    }
}
