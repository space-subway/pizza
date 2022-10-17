package com.app.pizza

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.app.pizza.databinding.FragmentMenuBinding
import com.app.pizza.model.Item

class MenuFragment: Fragment() {

    private var _binding: FragmentMenuBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var viewPager: ViewPager
    lateinit var viewPagerAdapter: SlidePagerAdapter
    lateinit var headerSliderList: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = binding.sliderViewpager

        // on below line we are initializing
        // our image list and adding data to it.
        headerSliderList = ArrayList<Int>()
        headerSliderList = headerSliderList + R.drawable.header_slide_1
        headerSliderList = headerSliderList + R.drawable.header_slide_2
        headerSliderList = headerSliderList + R.drawable.header_slide_3

        // on below line we are initializing our view
        // pager adapter and adding image list to it.
        viewPagerAdapter = SlidePagerAdapter(requireContext(), headerSliderList)

        // on below line we are setting
        // adapter to our view pager.
        viewPager.adapter = viewPagerAdapter

        //fill toolbar dropdown exposed menu
        val dropdownItems = listOf("Москва", "Санкт-Петербург")
        val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_menu_list_item, dropdownItems)
        (binding.menuTextField as? AutoCompleteTextView)?.setAdapter(adapter)
    }
}