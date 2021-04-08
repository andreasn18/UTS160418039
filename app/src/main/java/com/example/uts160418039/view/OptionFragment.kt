package com.example.uts160418039.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.uts160418039.R
import com.example.uts160418039.util.loadImage
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_option.*

class OptionFragment : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (arguments != null) {
            val nama = OptionFragmentArgs.fromBundle(requireArguments()).name
            val url = OptionFragmentArgs.fromBundle(requireArguments()).url
            txtNameOptions.setText(nama)
            profile_image.loadImage(url, progressBar3)
        }
    }
}