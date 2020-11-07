package com.example.notasuar.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notasuar.adapter.AudioItemAdapter
import com.example.notasuar.repository.MainViewModel
import id.ac.ui.cs.mobileprogramming.muhammad_rezki.notasuar.MainActivity
import id.ac.ui.cs.mobileprogramming.muhammad_rezki.notasuar.R
import id.ac.ui.cs.mobileprogramming.muhammad_rezki.notasuar.model.ItemEntity
import kotlinx.android.synthetic.main.main_fragment.*

class AudioFragment : Fragment(), ListFragment {
    override fun onPlayVideoOpen(index: Int) {

    }

    override fun onEditNoteOpen(index: Int) {

    }

    companion object {
        fun newInstance() = AudioFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: AudioItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_audio, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this.activity!!).get(MainViewModel::class.java)
        (activity!! as MainActivity).showFab()
        (activity!! as MainActivity).showToolbar()
        recycler_view.layoutManager = LinearLayoutManager(this.activity!!.applicationContext)
        adapter = AudioItemAdapter(this.activity!!.applicationContext,this)
        viewModel.getDataItems().observe(this, object : Observer<List<ItemEntity>> {
            override fun onChanged(@Nullable words: List<ItemEntity>) {
                adapter.setItems(words)
            }
        })
        recycler_view.adapter = adapter
    }

}
