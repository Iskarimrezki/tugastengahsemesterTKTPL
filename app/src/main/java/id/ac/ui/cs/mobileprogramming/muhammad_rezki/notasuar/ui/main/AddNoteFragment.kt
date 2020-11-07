@file:Suppress("DEPRECATION")

package id.ac.ui.cs.mobileprogramming.muhammad_rezki.notasuar.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.notasuar.repository.MainViewModel
import id.ac.ui.cs.mobileprogramming.muhammad_rezki.notasuar.MainActivity
import id.ac.ui.cs.mobileprogramming.muhammad_rezki.notasuar.R
import id.ac.ui.cs.mobileprogramming.muhammad_rezki.notasuar.model.TextEntity
import kotlinx.android.synthetic.main.fragment_add_note.*
import java.util.*

@Suppress("DEPRECATION")
class AddNoteFragment : Fragment() {

    companion object {
        fun newInstance() = AddNoteFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this.activity!!).get(MainViewModel::class.java)
        (activity!! as MainActivity).hideFab()
        (activity!! as MainActivity).hideToolbar()
        toolbar.setNavigationOnClickListener {
            this.activity!!.onBackPressed()
        }
        save.setOnClickListener {
            if (text_input.text.isNotEmpty()){
                viewModel.addTextItem(TextEntity(text_input.text.toString(), Calendar.getInstance().time))
                val toast = Toast.makeText(activity, "Note saved",Toast.LENGTH_SHORT)
                toast.show()
                activity!!.onBackPressed()
            }
        }

    }

}
