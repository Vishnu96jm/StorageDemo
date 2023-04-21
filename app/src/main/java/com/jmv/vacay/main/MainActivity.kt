package com.jmv.vacay.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jmv.vacay.App
import com.jmv.vacay.R
import com.jmv.vacay.addTrip.AddTripActivity
import com.jmv.vacay.databinding.ActivityMainBinding
import com.jmv.vacay.model.Trip
import com.jmv.vacay.utils.createAndShowDialog


class MainActivity : AppCompatActivity() {

    private val adapter by lazy { TripAdapter(::onItemLongTapped) }
    private val repository by lazy { App.repository }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

         binding = ActivityMainBinding.inflate(layoutInflater)
          val view = binding.root
          setContentView(view)

        initUi()
    }

    private fun initUi() {
        binding.tripsList.adapter = adapter
        binding.tripsList.layoutManager = LinearLayoutManager(this)

        binding.addTrip.setOnClickListener {
            startActivity(AddTripActivity.getIntent(this))
        }

    }

    private fun refreshData() {
        adapter.setData(repository.getTrips())
    }

    override fun onResume() {
        super.onResume()

        refreshData()
    }

    private fun onItemLongTapped(trip: Trip) {
        createAndShowDialog(this,
            getString(R.string.delete_title),
            getString(R.string.delete_message, trip.title),
            onPositiveAction = {
                repository.deleteTrip(trip.id)

                refreshData()
            })
    }

}
