package com.app.pizza

import android.os.Bundle
import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import com.app.pizza.databinding.ActivityMainBinding
import androidx.viewpager.widget.ViewPager
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager
    lateinit var viewPagerAdapter: SlidePagerAdapter
    lateinit var headerSliderList: List<Int>

    private var _binding: ActivityMainBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarMainActivity.title = ""
        setSupportActionBar(binding.toolbarMainActivity)

        viewPager = binding.sliderViewPager

        // on below line we are initializing
        // our image list and adding data to it.
        headerSliderList = ArrayList<Int>()
        headerSliderList = headerSliderList + R.drawable.header_slide_1
        headerSliderList = headerSliderList + R.drawable.header_slide_2
        headerSliderList = headerSliderList + R.drawable.header_slide_3

        // on below line we are initializing our view
        // pager adapter and adding image list to it.
        viewPagerAdapter = SlidePagerAdapter(this@MainActivity, headerSliderList)

        // on below line we are setting
        // adapter to our view pager.
        viewPager.adapter = viewPagerAdapter

        //fill toolbar dropdown exposed menu
        val dropdownItems = listOf("Option 1", "Option 2", "Option 3", "Option 4")
        val adapter = ArrayAdapter(this, R.layout.dropdown_menu_list_item, dropdownItems)
        (binding.menuTextField as? AutoCompleteTextView)?.setAdapter(adapter)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}