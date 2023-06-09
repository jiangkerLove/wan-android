package com.study.xml_view.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import com.study.xml_view.databinding.FragmentListBinding
import com.study.xml_view.ui.main.adapter.FooterAdapter

abstract class ListFragment<T : PagingDataAdapter<*, *>> : Fragment() {

    val mAdapter: T by lazy {
        onCreateAdapter()
    }

    private var _binding: FragmentListBinding? = null

    var loadStateListener: ((CombinedLoadStates) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentListBinding.inflate(inflater, container, false)
        binding.recyclerView.adapter = mAdapter
        _binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.apply {
            recyclerView.adapter =
                mAdapter.withLoadStateFooter(FooterAdapter { mAdapter.retry() })
            mAdapter.addLoadStateListener {
                when (it.refresh) {
                    is LoadState.NotLoading -> {
                        progressBar.visibility = View.INVISIBLE
                        recyclerView.visibility = View.VISIBLE
                    }
                    is LoadState.Loading -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.INVISIBLE
                    }
                    else -> {
                        val state = it.refresh as LoadState.Error
                        progressBar.visibility = View.INVISIBLE
                        Toast.makeText(
                            requireContext(),
                            "Load Error: ${state.error.message}",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
                loadStateListener?.invoke(it)
            }
        }
    }

    fun refresh() {
        mAdapter.refresh()
    }

    abstract fun onCreateAdapter(): T

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}