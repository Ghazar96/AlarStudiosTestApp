package com.example.alarstudiostestapp.mainPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alarstudiostestapp.MainActivity
import com.example.alarstudiostestapp.PaginationScrollListener
import com.example.alarstudiostestapp.PlacesData
import com.example.alarstudiostestapp.R
import com.example.alarstudiostestapp.mapPage.MapFragment
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class MainFragment : Fragment() {

    private val mainViewModel: MainFragmentViewModel by stateViewModel(
        bundle = {
            Bundle()
        }
    )

    private var adapter: MainAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            showLoading()
            mainViewModel.getNextPlaces()
        }

        val items = ArrayList<PlacesData>()
        adapter = MainAdapter(items = items, clickListener = {
            openMapFragment(it)
        })
        val lm = LinearLayoutManager(requireContext())
        listView.layoutManager = lm
        listView.adapter = adapter
        adapter?.notifyDataSetChanged()
        listView.addOnScrollListener(object : PaginationScrollListener(lm) {
            override fun isLastPage(): Boolean {
                return false
            }

            override fun isLoading(): Boolean {
                return loading.visibility == View.VISIBLE
            }

            override fun loadMoreItems() {
                mainViewModel.getNextPlaces()
            }

        })
        initObservers()
    }

    private fun initObservers() {
        mainViewModel.showErrorLiveData.observe(
            viewLifecycleOwner,
            { errorText ->
                handleError(errorText)
            }
        )

        mainViewModel.dataCompleteLiveData.observe(
            viewLifecycleOwner,
            {
                handleDataComplete(it)
            }
        )
    }

    private fun openMapFragment(data: PlacesData) {
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()

        fragmentTransaction.setCustomAnimations(
            android.R.animator.fade_in,
            android.R.animator.fade_out
        )

        val fragment = MapFragment()
        val bundle = Bundle()
        bundle.putDouble(LAT_KEY, data.lat)
        bundle.putDouble(LNG_KEY, data.lon)

        fragment.arguments = bundle
        fragmentTransaction.add(
            R.id.fragmentContainer, fragment,
            MainActivity.LOGIN_FRAGMENT_TAG
        )

        fragmentTransaction.addToBackStack(MainActivity.LOGIN_FRAGMENT_TAG)
        if (!requireActivity().supportFragmentManager.isStateSaved) {
            fragmentTransaction.commit()
        } else {
            fragmentTransaction.commitAllowingStateLoss()
        }
    }

    private fun handleError(message: String) {
        hideLoading()
    }

    private fun handleDataComplete(data: List<PlacesData>) {
        hideLoading()
        adapter?.addItems(data)
    }

    private fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        loading.visibility = View.GONE
    }
}

const val LAT_KEY = "lat_key"
const val LNG_KEY = "lng_key"