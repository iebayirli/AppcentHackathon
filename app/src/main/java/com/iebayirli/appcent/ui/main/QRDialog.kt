package com.iebayirli.appcent.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.iebayirli.appcent.R
import com.iebayirli.appcent.databinding.ItemQrDialogBinding
import com.iebayirli.appcent.utils.observeNotNull
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class QRDialog : BottomSheetDialogFragment() {

    private val viewModel: MainViewModel by sharedViewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<ItemQrDialogBinding>(
            inflater,
            R.layout.item_qr_dialog,
            container,
            false
        ).apply {
            viewModel = this@QRDialog.viewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.closeQRDialog.observeNotNull(this) {
            this.dismiss()
        }
    }


}