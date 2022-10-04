package com.example.submissionwindi.ui.story.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submissionwindi.R
import com.example.submissionwindi.data.source.remote.response.Story
import com.example.submissionwindi.databinding.FragmentListBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment(R.layout.fragment_list) {

    private val binding by viewBinding<FragmentListBinding>()
    private val viewModel by viewModel<ListViewModel>()

    private var listAdapter: ListAdapter? = null
    private val listStory: MutableList<Story> = mutableListOf()

    override fun onResume() {
        super.onResume()

        viewModel.getStory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = ListAdapter(listStory) {
            Toast.makeText(requireContext(), it.description, Toast.LENGTH_SHORT).show()
        }

        initBinding()
        initObservable()
    }

    private fun initBinding() = with(binding) {
        rvList.layoutManager = LinearLayoutManager(requireContext())
        rvList.adapter = listAdapter
    }

    private fun initObservable() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    listStory.clear()
                    listStory.addAll(it.list)
                    listAdapter?.notifyDataSetChanged()
                }
            }
        }
    }
}