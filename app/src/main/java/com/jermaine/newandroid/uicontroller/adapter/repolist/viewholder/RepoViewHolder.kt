package com.jermaine.newandroid.uicontroller.adapter.repolist.viewholder


import android.support.v7.widget.RecyclerView
import android.view.View
import com.jermaine.newandroid.datamodel.repo.Repo
import kotlinx.android.synthetic.main.item_layout.view.*

class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindRepo(repo: Repo, clickListener: View.OnClickListener) {
        with(repo) {
            itemView.idTextView.text = id
            itemView.nameTextView.text = name
            itemView.descriptionTextView.text = description
            itemView.cardView.setOnClickListener(clickListener)
        }
    }
}