package com.app.pizza

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.app.pizza.databinding.FragmentItemListBinding
import com.app.pizza.model.Item

class ItemListFragment: Fragment() {

    private var _binding: FragmentItemListBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter : ItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init items lis view
        val items: List<Item> = listOf(
            Item("title", "descrition", 123.0 ),
            Item("title", "descrition", 123.0 ),
            Item("title", "descrition", 123.0 ),
            Item("title", "descrition", 123.0 ),
            Item("title", "descrition", 123.0 ),
            Item("title", "descrition", 123.0 ),
            Item("title", "descrition", 123.0 ),
            Item("title", "descrition", 123.0 ),
            Item("title", "descrition", 123.0 ),
            Item("title", "descrition", 123.0 )
        )

        items?.let{
            itemAdapter = ItemsAdapter(items)
            itemAdapter.notifyDataSetChanged()
        }

        recyclerView = binding.recyclerItemsView
        recyclerView.adapter = itemAdapter
    }
}