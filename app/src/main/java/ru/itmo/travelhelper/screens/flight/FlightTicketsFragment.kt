package ru.itmo.travelhelper.screens.flight

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import ru.itmo.travelhelper.databinding.FragmentFlightTicketsBinding


class FlightTicketsFragment : Fragment() {
    lateinit var binding: FragmentFlightTicketsBinding
    private lateinit var adapter: FlightTicketsListAdapter
    private val dataList = listOf(
        listOf("Аэрофлот","27000p"),
        listOf("Победа","19000p"),
        listOf("Азимут","25000p"),
        listOf("ЧипАвиа","1234p"),
        listOf("РичАвиа","78000p"))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlightTicketsBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FlightTicketsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = FlightTicketsListAdapter(emptyList(), object : FlightTicketsOnItemClickListener {
            override fun onItemClicked(position: Int) {
                Toast.makeText(requireContext(), "Вы нажали на ${adapter.items[position]}", Toast.LENGTH_SHORT).show()
            }
        })

        binding.recyclerView.adapter = adapter

        binding.minFlightTicketCost.text = "${formatNumber(binding.seekBar.min.toString())}₽"
        binding.maxFlightTicketCost.text = "${formatNumber(binding.seekBar.max.toString())}₽"

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    showCurrentValueSnackbar(progress)
                }
                filterData(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

    }
    private fun showCurrentValueSnackbar(progress: Int) {
        val snackbar = Snackbar.make(binding.root, formatNumber(progress.toString()), Snackbar.ANIMATION_MODE_SLIDE)
        val params = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        params.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
        params.setMargins(530, 155, 0, 0)
        snackbar.view.apply {
            backgroundTintList = ColorStateList.valueOf(Color.TRANSPARENT)
            layoutParams = params
            elevation = 0F
        }
        snackbar.setTextColor(ColorStateList.valueOf(Color.BLACK))
//        snackbar.setAnchorView(binding.minFlightTicketCost)


        snackbar.show()
    }

    private fun filterData(progress: Int) {
        val filteredList = dataList.filter { it[1].dropLast(1).toInt() <= progress }
        adapter.updateList(filteredList)
    }

    private fun formatNumber(numberStr: String): String {
        return numberStr.reversed().chunked(3).joinToString(" ").reversed()
    }

}