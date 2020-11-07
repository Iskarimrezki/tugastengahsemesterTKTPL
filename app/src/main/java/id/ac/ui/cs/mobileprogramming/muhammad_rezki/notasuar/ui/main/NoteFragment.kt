package com.example.notasuar.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notasuar.adapter.NoteItemAdapter
import com.example.notasuar.repository.MainViewModel
import id.ac.ui.cs.mobileprogramming.muhammad_rezki.notasuar.MainActivity
import id.ac.ui.cs.mobileprogramming.muhammad_rezki.notasuar.R
import id.ac.ui.cs.mobileprogramming.muhammad_rezki.notasuar.model.ItemEntity
import kotlinx.android.synthetic.main.main_fragment.*

class NoteFragment : Fragment(), ListFragment {
    override fun onPlayVideoOpen(index: Int) {

    }

    companion object {
        fun newInstance() = NoteFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: NoteItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onEditNoteOpen(index:Int) {
        val fragmentTransaction: FragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.container, EditNoteFragment.newInstance(index))
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this.activity!!).get(MainViewModel::class.java)
        (activity!! as MainActivity).showFab()
        (activity!! as MainActivity).showToolbar()

        recycler_view.layoutManager = GridLayoutManager(this.activity!!.applicationContext,2)
        adapter = NoteItemAdapter(this.activity!!.applicationContext,this)
        viewModel.getDataItems().observe(this, object : Observer<List<ItemEntity>> {
            override fun onChanged(@Nullable words: List<ItemEntity>) {
                Log.v("KENA","KENA2")
                adapter.setItems(words)
            }
        })
        recycler_view.adapter = adapter
    }

}
