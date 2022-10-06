package com.sytyy.giphytest.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.sytyy.giphytest.databinding.FragmentGiphyListBinding
import com.sytyy.giphytest.viewmodel.GiphyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GiphyListFragment : Fragment() {

    private lateinit var listBinding: FragmentGiphyListBinding

    private val listViewModel: GiphyViewModel by activityViewModels()

    private var phrase: String = "init"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listBinding = FragmentGiphyListBinding.inflate(inflater, container, false)
        listBinding.apply {
            gifSearch.setOnQueryTextListener(object :
                SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    phrase = p0.toString()
                    lifecycleScope.launch {
                        listViewModel.searchGifs(phrase).observe(viewLifecycleOwner) {
                            initAdapter(phrase).submitData(lifecycle, it)
                        }
                    }
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean = false

            })
            when (initAdapter(phrase).phrase) {
                "init" -> {
                    lifecycleScope.launch {
                        listViewModel.getAllGifs().observe(viewLifecycleOwner) {
                            initAdapter("init").submitData(lifecycle, it)
                        }
                    }
                }
                else -> {
                    lifecycleScope.launch {
                        listViewModel.searchGifs(phrase).observe(viewLifecycleOwner) {
                            initAdapter(phrase).submitData(lifecycle, it)
                        }
                    }
                }
            }

        }
        return listBinding.root
    }


    private fun handleError(loadState: CombinedLoadStates) {
        val errorState = loadState.source.append as? LoadState.Error
            ?: loadState.source.prepend as? LoadState.Error
        errorState?.let {
            Toast.makeText(requireContext(), "${it.error}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initAdapter(phrase: String): GiphyListAdapter {
        val adapter = GiphyListAdapter(requireContext(), phrase)
        adapter.run {
            addLoadStateListener {
                listBinding.apply {
                    gifRecycleView.isVisible = it.source.refresh is LoadState.NotLoading
                    progressBar.isVisible = it.source.refresh is LoadState.Loading
                    buttonRetry.isVisible = it.source.refresh is LoadState.Error
                }
                handleError(it)
            }
            listBinding.buttonRetry.setOnClickListener {
                retry()
            }
            listBinding.gifRecycleView.adapter =
                withLoadStateFooter(GiphyLoaderStateAdapter { retry() })
            return adapter
        }
    }
}
