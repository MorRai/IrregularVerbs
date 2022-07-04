package com.rai.irregularverbs.ui



import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rai.irregularverbs.constants.MenuType.EXAM
import com.rai.irregularverbs.constants.MenuType.FLASHCARD
import com.rai.irregularverbs.constants.MenuType.IMAGE
import com.rai.irregularverbs.databinding.FragmentVerbsMainBinding
import android.app.AlarmManager
import android.content.Context.ALARM_SERVICE
import android.app.PendingIntent
import android.content.Intent
import com.rai.irregularverbs.receiver.VerbReceiver

import java.util.*


class VerbsMainFragment : Fragment() {

    private var _binding: FragmentVerbsMainBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 19)
            set(Calendar.MINUTE, 30)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)}

        if (calendar.time < Date()) calendar.add(Calendar.DATE, 1)

        val intent = Intent(
           this.context,
            VerbReceiver::class.java
        )
        val pendingIntent = PendingIntent.getBroadcast(
            this.context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = this.context?.getSystemService(ALARM_SERVICE) as AlarmManager?
        alarmManager?.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_HALF_DAY,
            pendingIntent
        )
        return FragmentVerbsMainBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
    }



    private fun bind() {
        binding.apply {
            examButton.setOnClickListener {
                val action = VerbsMainFragmentDirections.actionVerbsMainFragmentToCommonMenuFragment(EXAM)
                findNavController().navigate(action)
            }
            listButton.setOnClickListener {
                val action = VerbsMainFragmentDirections.actionVerbsMainFragmentToListVerbFragment()
                findNavController().navigate(action)
            }
            imageButton.setOnClickListener {
                val action = VerbsMainFragmentDirections.actionVerbsMainFragmentToCommonMenuFragment(IMAGE)
                findNavController().navigate(action)
            }
            flascardButton.setOnClickListener {
                val action = VerbsMainFragmentDirections.actionVerbsMainFragmentToCommonMenuFragment(FLASHCARD)
                findNavController().navigate(action)
            }
        }
    }


}