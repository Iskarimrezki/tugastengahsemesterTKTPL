package com.example.notasuar.ui.main

import androidx.fragment.app.FragmentActivity

interface ListFragment {
    fun onEditNoteOpen(index:Int)
    fun getActivity():FragmentActivity?
    fun onPlayVideoOpen(index: Int)
}