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

    companion object {
        const val ARG_ITEMS = "items"

        fun getInstance( items: ArrayList<Item>? ): Fragment{
            var bundle = Bundle()
            bundle.putParcelableArrayList( ARG_ITEMS, items)
            var fragment = ItemListFragment()
            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var items = requireArguments().getParcelableArrayList<Item>(ARG_ITEMS)

        items?.let{
            itemAdapter = ItemsAdapter(items)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)

        var recyclerView = binding.recyclerItemsView
        recyclerView.adapter = itemAdapter

        return binding.root
    }

}