package net.techiesatishktest.ui.recycler


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.techiesatishktest.R
import net.techiesatishktest.adapter.CodesAdapter
import net.techiesatishktest.db.entity.Codes
import net.techiesatishktest.ui.home.HomeViewModel


class RecyclerFragment : Fragment() {
    private lateinit var mViewModel: HomeViewModel
    private val adapter = CodesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val  view=inflater.inflate(R.layout.fragment_results, container, false)

        mViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view) as RecyclerView
        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter


    return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        mViewModel.allCodes.observe(this,
            Observer<List<Codes>> { t -> adapter.setNotes(t!!) })

    }
}
