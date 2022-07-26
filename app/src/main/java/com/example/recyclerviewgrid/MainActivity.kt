package com.example.recyclerviewgrid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recyclerviewgrid.adapter.PersonAdapter
import com.example.recyclerviewgrid.data.Person
import com.example.recyclerviewgrid.data.listOfItems
import com.example.recyclerviewgrid.databinding.ActivityAddItemBinding
import com.example.recyclerviewgrid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var binding2: ActivityAddItemBinding
    private lateinit var personAdapter: PersonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        listeners()
        personAdapter.submitList(listOfItems.toList())
    }

    private fun init() {
        personAdapter = PersonAdapter(context = this)
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = personAdapter
        }
    }

    private fun listeners() {
        binding.btnAdd.setOnClickListener {
            addInfo()
        }
        binding.imgRefresh.setOnClickListener {
            personAdapter.submitList(listOfItems.toList())
//            personAdapter.submitList(listOfItems.toList().shuffled())    // for random refresh
            Log.d("add", "refresh, ${listOfItems.size}")
        }
    }

    private fun addInfo() {

        binding2 = ActivityAddItemBinding.inflate(layoutInflater)

        val addDialog = AlertDialog.Builder(this)
            .setIcon(R.drawable.ic_round_person_add_24)
            .setTitle(getString(R.string.add_user))
        addDialog.setView(binding2.root)
        addDialog.setPositiveButton(getString(R.string.add_user)) { dialog, _ ->
            val firstName = binding2.etFirstname.text.toString()
            val lastName = binding2.etLastname.text.toString()
            val sign = binding2.etSign.text.toString()
            val withPhoto = binding2.checkBox.isChecked

            when (false) {
                !isEmptyField(firstName, lastName, sign) -> Toast.makeText(this,
                    getString(R.string.empty_fields),
                    Toast.LENGTH_SHORT).show()
                else -> {
                    listOfItems.add(Person(firstName = firstName,
                        withImage = withPhoto,
                        lastName = lastName,
                        zodiac = sign))
                    Log.d("add", "user added, ${listOfItems.size}")
                    Toast.makeText(this, getString(R.string.add_success), Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
            }
        }
        addDialog.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
            dialog.dismiss()
        }
        addDialog.create()
        addDialog.show()
    }
}