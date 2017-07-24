package com.jermaine.newandroid.repository


import android.util.Log
import com.jermaine.newandroid.api.ApiService
import com.jermaine.newandroid.datamodel.database.NewAndroidDatabase
import com.jermaine.newandroid.datamodel.repo.Repo
import com.jermaine.newandroid.di.component.AppComponent
import com.jermaine.newandroid.repository.callback.OnFetchReposCallback
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor
import javax.inject.Inject

class ReposRepositoryImpl(appComponent: AppComponent) : ReposRepository {

    val TAG: String = "ReposRepository"

    @Inject
    lateinit var mApiService: ApiService
    @Inject
    lateinit var database: NewAndroidDatabase

    init {
        appComponent.inject(this)
    }

    override fun save(repo: Repo) {
        Single.fromCallable { database.repoDao().save(repo) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

    override fun save(repoList: List<Repo>) {
        Single.fromCallable { {database.repoDao().save(repoList)} }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

    override fun fetchRepos(callback: OnFetchReposCallback) {
        // local
        val executor = Executor {
            Thread(it).start()
        }
        executor.execute({
            callback.onSuccess(database.repoDao().getRepos())
        })

        // from server
        val call = mApiService.repos
        call.enqueue(object : Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                // save in db
                save(response.body())
            }

            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                Log.d(TAG, t.message)
            }
        })
    }
}
