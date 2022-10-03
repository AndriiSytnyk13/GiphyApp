package com.sytyy.giphytest.view.gif

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.sytyy.giphytest.databinding.FragmentGiphyGifBinding
import com.sytyy.giphytest.viewmodel.GiphyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GiphyGifFragment : Fragment() {

    private val args: GiphyGifFragmentArgs by navArgs()
    private val viewModel: GiphyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        FragmentGiphyGifBinding.inflate(inflater, container, false).run {
            val adapter = GiphyGifFullAdapter(requireContext(), ArrayList())
            viewPager.adapter = adapter
            lifecycleScope.launch {
                delay(200)
                viewPager.setCurrentItem(args.position, false)
                viewPager.visibility = View.VISIBLE
            }
            viewModel.gifs.observe(viewLifecycleOwner) {
                adapter.getGifs(it)
            }
            viewModel._position.observe(viewLifecycleOwner) {
                viewPager.setCurrentItem(it, false)
            }
            return root
        }
    }
}
