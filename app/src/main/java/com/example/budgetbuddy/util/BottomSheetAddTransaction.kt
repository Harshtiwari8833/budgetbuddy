package com.example.budgetbuddy.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.budgetbuddy.databinding.AddTransactionBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetAddTransaction : BottomSheetDialogFragment() {
    lateinit var behavior: BottomSheetBehavior<FrameLayout>
    lateinit var binding: AddTransactionBottomSheetBinding

    private fun getWindowHeight() =resources.displayMetrics.heightPixels

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=AddTransactionBottomSheetBinding.inflate(layoutInflater)
        binding.close.setOnClickListener {
            dialog?.cancel()
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val view : FrameLayout? = dialog?.findViewById(com.google.android.material.R.id.design_bottom_sheet)
        view?.layoutParams?.height = ViewGroup.LayoutParams.MATCH_PARENT
        view?.layoutParams?.width = ViewGroup.LayoutParams.MATCH_PARENT

        behavior = BottomSheetBehavior.from(view!!)
        behavior.peekHeight = getWindowHeight()

        behavior.state = BottomSheetBehavior.STATE_EXPANDED

        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED){
                    dismiss()
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }

        })
    }
}