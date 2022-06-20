package com.vagner.news


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vagner.news.adapter.MainAdapter
import com.vagner.news.databinding.ActivityMainBinding
import com.vagner.news.utils.Constants.CATEGORY
import com.vagner.news.utils.Constants.Q
import com.vagner.news.utils.Constants.TAG
import com.vagner.news.viewmodel.MainViewModel


class MainActivity : AppCompatActivity(), View.OnClickListener {



    private lateinit var searchView: SearchView

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel


    private val adapterMain = MainAdapter {
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



        launcher()


    }



    override fun onClick(view: View) {


        val button = view as Button
        val category = button.text.toString()

        viewModel.getNewsCountry(category, Q)


    }

    private fun launcher() {

        getNews()
        searchView()



    }

    private fun searchView() {
        searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(q: String): Boolean {
                viewModel.getNewsCountry(CATEGORY, q)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }


    private fun getNews() {

        successNews()
        errorNews()
    }


    private fun successNews() {
        viewModel.liveList.observe(this, Observer {

            if (it.isEmpty()) {
                Toast.makeText(this, "Not found !", Toast.LENGTH_SHORT).show()

            } else {

                Log.d(TAG, "Success : $it")
                adapterMain.article = it
            }

        })

        viewModel.getNewsCountry(CATEGORY, Q)

    }

    private fun errorNews() {

        viewModel.liveError.observe(this, Observer {
            Toast.makeText(this, "An error occurred !", Toast.LENGTH_SHORT).show()
        })
    }


}


