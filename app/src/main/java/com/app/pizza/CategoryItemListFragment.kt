package com.app.pizza

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.app.pizza.databinding.FragmentCategoryItemListBinding
import com.app.pizza.model.Item
import com.app.pizza.model.ItemCategory
import com.app.pizza.utils.Status
import com.app.pizza.viewmodel.ItemViewModel
import com.google.android.material.tabs.TabLayoutMediator

class CategoryItemListFragment : Fragment() {
    private var _binding: FragmentCategoryItemListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewPager: ViewPager2
    private var items : List<Item> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryItemListBinding.inflate(inflater, container, false)

        viewPager   = binding.tabViewpager

        return binding.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        loadItems()
    }

    private fun loadItems(){

        val viewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)

        viewModel.getItems().observe(viewLifecycleOwner, { resource ->
            resource?.let { resource ->

                when (resource.status) {
                    Status.SUCCESS_REMOTE, Status.SUCCESS_LOCAL -> {
                        //update ui
                        //binding.progressBar.visibility = View.GONE

                        resource.data?.let {
                            items = it
                            setupCategoriesAdapter( items!! )
                        }
                    }
                }
            }
        })
    }

    private fun setupCategoriesAdapter( list: List<Item> ){

        val itemsMap: Map<ItemCategory, MutableList<Item>> = convertResponse( list )

        val categoryTab = binding.tabLayout

        val adapter = ViewPagerAdapter(activity as MainActivity, itemsMap.toMutableMap())
        viewPager.adapter = adapter

        TabLayoutMediator(categoryTab, viewPager) { tab, position ->
            tab.text = itemsMap.keys.elementAt(position).name
        }.attach()
    }

    private fun convertResponse( itemList: List<Item> ): MutableMap<ItemCategory, MutableList<Item>>{

        val itemsMap : MutableMap<ItemCategory, MutableList<Item>> = HashMap()

        val iterator = itemList.listIterator()
        while(iterator.hasNext()){
            val item = iterator.next()
            if( item.category != null ){

                if( itemsMap[item.category] == null )
                    itemsMap[item.category!!] = ArrayList()

                itemsMap[item.category]!!.add( item )
            }
        }

        return itemsMap
    }

    class ViewPagerAdapter(activity: AppCompatActivity, private val itemsMap: MutableMap<ItemCategory, MutableList<Item>> ): FragmentStateAdapter( activity ){
        override fun getItemCount(): Int {
            return itemsMap.keys.size
        }

        override fun createFragment(position: Int): Fragment {
            var category = itemsMap.keys.elementAt(position)
            var items = itemsMap[ category ]

            return ItemListFragment.getInstance(items as ArrayList<Item>?)
        }
    }
}