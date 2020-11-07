package id.ac.ui.cs.mobileprogramming.muhammad_rezki.notasuar

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.example.notasuar.repository.CustomViewModelFactory
import com.example.notasuar.repository.MainViewModel
import com.example.notasuar.ui.main.*
import com.google.android.material.navigation.NavigationView
import id.ac.ui.cs.mobileprogramming.muhammad_rezki.notasuar.ui.main.AddNoteFragment
import id.ac.ui.cs.mobileprogramming.muhammad_rezki.notasuar.ui.main.MainFragment
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_content.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.note -> {
                onNotePageOpen()
                toolbar.title = getString(R.string.note)
            }
            R.id.recent -> {
                onRecentPageOpen()
                toolbar.title = getString(R.string.recent)
            }
            R.id.audio -> {
                onAudioPageOpen()
                toolbar.title = getString(R.string.audio)
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun hideFab() {
        fabClose()
        fab.visibility = View.GONE
    }

    fun showFab() {
        fab.visibility = View.VISIBLE
    }

    fun hideToolbar() {
        toolbar.visibility = View.GONE
    }

    fun showToolbar() {
        toolbar.visibility = View.VISIBLE
    }

    fun fabOpen() {
        fab_open.hide()
        fab_close.show()
        fab_audio.show()
        fab_text.show()
    }

    fun fabClose() {
        fab_open.show()
        fab_close.hide()
        fab_audio.hide()
        fab_text.hide()
    }

    fun onAddNoteOpen() {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, AddNoteFragment.newInstance())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    fun onRecordAudioOpen() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            val permissions = arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
            ActivityCompat.requestPermissions(this, permissions,0)
        }
        else{
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, RecordAudioFragment.newInstance())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

    fun onNotePageOpen() {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, NoteFragment.newInstance())
        fragmentTransaction.commit()
    }

    fun onRecentPageOpen() {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, MainFragment.newInstance())
        fragmentTransaction.commit()
    }

    fun onAudioPageOpen() {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, AudioFragment.newInstance())
        fragmentTransaction.commit()
    }

    fun onPlayAudioOpen(index: Int) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val dialogFragment = PlayAudioFragment(index)
        dialogFragment.show(fragmentTransaction, "dialog")
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, CustomViewModelFactory(application))
            .get(MainViewModel::class.java)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        fab_open.setOnClickListener {
            fabOpen()
        }
        fab_close.setOnClickListener {
            fabClose()
        }
        fab_text.setOnClickListener {
            onAddNoteOpen()
        }
        fab_audio.setOnClickListener {
            onRecordAudioOpen()
        }

        toolbar.setNavigationOnClickListener {
            drawer_layout.openDrawer(Gravity.LEFT)
        }
        nav_view.setNavigationItemSelectedListener(this)
        nav_view.menu.get(0).isChecked = true
        toolbar.title = "Recent"

    }
}
