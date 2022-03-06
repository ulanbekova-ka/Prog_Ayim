package com.kay.prog.ayim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kay.prog.ayim.databinding.ActivityMainBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val githubApi get() = Injector.githubApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            val adapter = Adapter()

            recycler.adapter = adapter
            recycler.layoutManager = LinearLayoutManager(this@MainActivity)
            recycler.addItemDecoration(
                DividerItemDecoration(this@MainActivity, RecyclerView.VERTICAL)
            )

            githubApi.getRepositories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { response ->

                    val list = mutableListOf<Item>()
                    response.items.forEach {
                        val repo = Item(it.id, it.name, it.full_name, it.private, it.owner, it.html_url, it.description)
                        list.add(repo)
                    }

                    adapter.setData(list)
                }
                .subscribe()
        }
    }
}