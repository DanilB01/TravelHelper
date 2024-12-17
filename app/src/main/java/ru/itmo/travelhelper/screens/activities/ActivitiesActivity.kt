package ru.itmo.travelhelper.screens.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.itmo.travelhelper.databinding.ActivityActivitiesBinding
import ru.itmo.travelhelper.presenter.activities.ActivitiesPresenter
import ru.itmo.travelhelper.view.activities.ActivitiesView

class ActivitiesActivity : AppCompatActivity(), ActivitiesView {

    private lateinit var binding: ActivityActivitiesBinding
    private lateinit var presenter: ActivitiesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = ActivitiesPresenter(this)

        if (savedInstanceState == null) {
            presenter.onFragmentRequested(CategoryFragment())
        }
    }

    override fun replaceFragment(fragment: androidx.fragment.app.Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(binding.navHostFragment.id, fragment)
        transaction.commit()
    }
}
