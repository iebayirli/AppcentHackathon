package com.iebayirli.appcent.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.iebayirli.appcent.R
import com.iebayirli.appcent.utils.observeNotNull

abstract class BaseFragment<VM : BaseViewModel, B : ViewDataBinding> : Fragment() {

    @get:LayoutRes
    protected abstract val layoutId: Int

    lateinit var binding: B

    abstract val viewModel: VM

    abstract fun initializeUI(savedInstanceState: Bundle?)

    abstract fun observe()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, layoutId, container, false
        )
        binding.lifecycleOwner = this
        R.id.action_loginFragment_to_userFormFragment
        binding.setVariable(BR.viewModel, viewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUI(savedInstanceState)
        observe()
        viewModel.getDestinationId().observeNotNull(this) {
            findNavController().navigate(it)
        }
    }

}