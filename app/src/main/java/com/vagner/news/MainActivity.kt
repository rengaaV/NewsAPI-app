package com.vagner.news

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vagner.news.adapter.MainAdapter
import com.vagner.news.databinding.ActivityMainBinding
import com.vagner.news.utils.Constants.CATEGORY
import com.vagner.news.utils.Constants.TAG
import com.vagner.news.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var dialog: ProgressDialog

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel


    val adapterMain = MainAdapter {
        val intent = Intent(applicationContext, DetailsActivity::class.java)
        intent.putExtra("data", it)
        startActivity(intent)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.recyclerMain.adapter = adapterMain
        binding.recyclerMain.setHasFixedSize(true)

        binding.buttonBusiness.setOnClickListener(this)
        binding.buttonEntertainment.setOnClickListener(this)
        binding.buttonGeneral.setOnClickListener(this)
        binding.buttonHealth.setOnClickListener(this)
        binding.buttonScience.setOnClickListener(this)
        binding.buttonSports.setOnClickListener(this)
        binding.buttonTechnology.setOnClickListener(this)


        laucher()

    }

    override fun onStart() {
        super.onStart()

        viewModel.getNewsCountry(CATEGORY)

    }

    private fun laucher() {

        getNews()


    }

    private fun getNews() {

        sucessNews()
        errorNews()
    }


    private fun sucessNews() {
        viewModel.liveList.observe(this, Observer {


            Log.d(TAG, "onStart : $it")
            adapterMain.article = it

        })
    }

    private fun errorNews() {

        viewModel.liveError.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }


    override fun onClick(view: View) {

        val button = view as Button
        val category = button.text.toString()

        viewModel.getNewsCountry(category)


    }

}
