package id.ac.ui.cs.mobileprogramming.muhammad_rezki.notasuar.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.main_fragment.*
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import com.example.notasuar.adapter.RecentItemAdapter
import com.example.notasuar.repository.MainViewModel
import com.example.notasuar.ui.main.EditNoteFragment
import com.example.notasuar.ui.main.ListFragment
import id.ac.ui.cs.mobileprogramming.muhammad_rezki.notasuar.MainActivity
import id.ac.ui.cs.mobileprogramming.muhammad_rezki.notasuar.R
import id.ac.ui.cs.mobileprogramming.muhammad_rezki.notasuar.model.ItemEntity


class MainFragment : Fragment(), ListFragment {
    override fun onPlayVideoOpen(index: Int) {
        val fragmentTransaction: FragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: RecentItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
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
        recycler_view.layoutManager = LinearLayoutManager(this.activity!!.applicationContext)
        adapter = RecentItemAdapter(this.activity!!.applicationContext,this)
        viewModel.getDataItems().observe(this, object : Observer<List<ItemEntity>> {
            override fun onChanged(@Nullable words: List<ItemEntity>) {
                adapter.setItems(words)
            }
        })
        recycler_view.adapter = adapter
    }

}
