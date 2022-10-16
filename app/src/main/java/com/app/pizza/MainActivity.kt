package com.app.pizza

import android.os.Bundle
import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager.widget.ViewPager
import com.app.pizza.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var viewPager: ViewPager
    lateinit var viewPagerAdapter: SlidePagerAdapter
    lateinit var headerSliderList: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarMainActivity)
        //binding.toolbarMainActivity.title = ""

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

        val navView: BottomNavigationView = binding.bottomNavigation

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_menu, R.id.navigation_profile, R.id.navigation_basket
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}