package com.delightroom.android.gitproject.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.delightroom.android.gitproject.R
import com.delightroom.android.gitproject.common.DefaultItemDecoration
import com.delightroom.android.gitproject.databinding.FragmentCommentsBinding
import com.delightroom.android.gitproject.datasource.vo.CommentVO
import com.delightroom.android.gitproject.present.adapter.CommentsPagingAdapter
import com.delightroom.android.gitproject.present.viewmodel.ReposDetailViewModel
import com.delightroom.android.gitproject.utility.logI
import com.delightroom.android.gitproject.utility.px
import kotlinx.android.synthetic.main.fragment_comments.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CommentsFragment : Fragment() {

    private val reposDetailViewModel by sharedViewModel<ReposDetailViewModel>()
    private lateinit var binding: FragmentCommentsBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        logI("onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logI("onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logI("onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_comments, container, false)
        binding.viewModel = reposDetailViewModel
        binding.lifecycleOwner = this

        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        logI("onActivityCreated")

        initLayout()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logI("onViewCreated")

        initViewModelCallbackData()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        logI("onSaveInstanceState")
    }


    /**
     * init layout
     */
    private fun initLayout() {
        swipeRefreshComments.setOnRefreshListener { reposDetailViewModel.refreshListOfCommentVO() }

        with(recyclerComments) {
            layoutManager = LinearLayoutManager(context)
            adapter = CommentsPagingAdapter(onCommentsPagingAdapter)
            addItemDecoration(DefaultItemDecoration(10.px))
        }
    }


    /**
     * init viewModel data of callback
     */
    private fun initViewModelCallbackData() {
        reposDetailViewModel.listOfCommentVO.observe(viewLifecycleOwner, Observer { list ->
            val adapter = recyclerComments.adapter as CommentsPagingAdapter
            adapter.submitList(list)

            swipeRefreshComments.isRefreshing = false
        })
    }


    /**
     * onCommentsAdapterListener
     * for dealing with event of recyclerComments
     */
    private val onCommentsPagingAdapter =
        object : CommentsPagingAdapter.OnCommentsAdapterListener {
            override fun onSelectItem(commentVO: CommentVO) {

            }
        }
}
