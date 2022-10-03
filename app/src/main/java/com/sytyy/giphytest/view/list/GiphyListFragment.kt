package com.sytyy.giphytest.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.sytyy.giphytest.databinding.FragmentGiphyListBinding
import com.sytyy.giphytest.viewmodel.GiphyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GiphyListFragment : Fragment() {

    private lateinit var listBinding: FragmentGiphyListBinding

    private val listViewModel: GiphyViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listBinding = FragmentGiphyListBinding.inflate(inflater, container, false)
        val adapter = GiphyListAdapter(requireContext(), ArrayList())
        listBinding.apply {
            gifRecycleView.adapter = adapter
            gifSearch.setOnQueryTextListener(object :
                SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    p0?.let {
                        listViewModel.searchGifs(it)
                    }
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean = false

            })
        }
        listViewModel.gifs.observe(viewLifecycleOwner) {
            adapter.updateGifs(it)
        }
        return listBinding.root
    }

}
