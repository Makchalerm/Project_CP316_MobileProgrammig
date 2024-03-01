package com.example.calculatorcalorie.Fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.calculatorcalorie.R


class DiaryFragment : Fragment() {
    lateinit var slider: EditText
    lateinit var slider1: EditText
    lateinit var slider3: EditText
    lateinit var submitButton: Button
    lateinit var card: CardView
    lateinit var bmrText: TextView
    lateinit var disclaimer: TextView
    lateinit var label: TextView
    lateinit var res: TextView
    lateinit var maleButton: Button
    lateinit var femaleButton: Button
    lateinit var user: String
    var bmr: Float = 0.0f
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_diary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        slider = view.findViewById(R.id.slider1)
        slider1 = view.findViewById(R.id.slider2)
        slider3 = view.findViewById(R.id.slider3)
        submitButton = view.findViewById(R.id.submitButton)
        card = view.findViewById(R.id.cardView)
        bmrText = view.findViewById(R.id.bmrtext)
        disclaimer = view.findViewById(R.id.disclamer)
        res = view.findViewById(R.id.Result)
        label = view.findViewById(R.id.yourBmi)
        maleButton = view.findViewById(R.id.male)
        femaleButton = view.findViewById(R.id.female)
        maleButton.setOnClickListener {
            maleButton.setBackgroundColor(Color.parseColor("#FAF186"))
            femaleButton.setBackgroundColor(Color.WHITE)
            user = "Male"
            Toast.makeText(activity, user, Toast.LENGTH_SHORT).show()
        }
        femaleButton.setOnClickListener {
            femaleButton.setBackgroundColor(Color.parseColor("#FAF186"))
            maleButton.setBackgroundColor(Color.WHITE)
            user = "Female"
            Toast.makeText(activity, user, Toast.LENGTH_SHORT).show()
        }
        submitButton.setOnClickListener {
            if (slider.text.toString().isNotEmpty() && slider1.toString()
                    .isNotEmpty() && slider3.toString().isNotEmpty()
            ) {
                cardChanger()
            } else {
                Toast.makeText(activity, "Input is empty!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun cardChanger() {
        bmrText.visibility = View.GONE
        disclaimer.visibility = View.GONE
        res.visibility = View.VISIBLE
        label.visibility = View.VISIBLE
        val height: Float = slider.text.toString().toFloat()
        val weight: Float = slider1.text.toString().toFloat()
        val age: Float = slider3.text.toString().toFloat()
        if (user == "Male") {
            bmr = (((10 * weight) + (6.25 * height) - (5 * age) + 5).toFloat())
            Toast.makeText(activity, bmr.toString(), Toast.LENGTH_SHORT).show()
        } else {
            bmr = (((10 * weight) + (6.25 * height) - (5 * age) - 161).toFloat())
            Toast.makeText(activity, bmr.toString(), Toast.LENGTH_SHORT).show()
        }

        res.text = bmr.toString()
    }
}