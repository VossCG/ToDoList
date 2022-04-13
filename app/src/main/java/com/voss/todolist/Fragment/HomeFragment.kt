package com.voss.todolist.Fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.voss.todolist.Adapter.HomeEventAdapter
import com.voss.todolist.Data.EventTypes
import com.voss.todolist.ViewModel.EventViewModel
import com.voss.todolist.databinding.HomefragmentBinding
import kotlinx.coroutines.Runnable
import java.util.*

class HomeFragment : BaseFragment<HomefragmentBinding>(HomefragmentBinding::inflate) {
    private val viewModel: EventViewModel by activityViewModels()
    private val calendar by lazy { Calendar.getInstance() }
    private val handler = Handler(Looper.getMainLooper())
    private val navController: NavController by lazy { findNavController() }

    private val mAdapter by lazy { HomeEventAdapter(
            navController,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH)
        )
    }

    private val TAG = HomeFragment::class.java.simpleName
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
    }

    private fun setRecyclerView() {
        val recyclerView = binding.homeRecyclerview
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = mAdapter

        // 設定item滑動 刪除與編輯
        // ViewModel
        // readAllEvent was created by ViewModel init
        // it's call eventDao.getAll and return LiveData<List<EventType>>
        // Then use readAllEvent to reference it
        // So we don't use event.getAll in Fragment or Activity , it already create in ViewModel
        // we just call observe scope , then set adapter data to complete RecyclerView Refresh

        viewModel.readAllEvent.observe(this) {
            val monthsList = it.filter {
                it.year == calendar.get(Calendar.YEAR) && it.month == calendar.get(Calendar.MONTH)
            }
            mAdapter.setData(monthsList)
        }
    }

    private val simpleItemCallback = object :
        ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.absoluteAdapterPosition
            Log.d(TAG, "position:$position")
            val eventData = viewModel.readAllEvent.value?.get(position)!!

            when (direction) {
                ItemTouchHelper.LEFT -> {
                    val runnable = Runnable {
                        runDeleteEvent(eventData)
                        mAdapter.notifyItemChanged(position)
                    }
                    handler.postDelayed((runnable), 1000)
                    Snackbar.make(
                        binding.homeRecyclerview,
                        "List was deleted",
                        1000
                    )
                        .setAction("Undo", View.OnClickListener {
                            mAdapter.notifyItemChanged(position)
                            handler.removeCallbacks(runnable)
                        }).show()
                }

                ItemTouchHelper.RIGHT -> {
                    Log.d(TAG, "Parcelable[${eventData.date}]")

                    val directions =
                        HomeFragmentDirections.actionHomeFragmentToUpdateEventFragment(eventData)
                    navController.navigate(directions)
                }
            }
        }
    }

    private fun runDeleteEvent(eventType: EventTypes) {
        viewModel.deleteEvent(eventType)
    }
}
