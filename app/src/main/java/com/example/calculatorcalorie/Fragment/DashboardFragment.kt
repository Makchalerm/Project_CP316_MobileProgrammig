package com.example.calculatorcalorie.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calculatorcalorie.Data.Food
import com.example.calculatorcalorie.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

class DashboardFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var databaseReference: DatabaseReference
    lateinit var response: MutableList<Food>
    lateinit var db: FirebaseDatabase
    lateinit var enName:EditText
    lateinit var enCal:EditText
    lateinit var enImage:EditText
    var maxId = 0
    private var adapter: AdapterDB? = null
    private var mAuth: FirebaseAuth? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enName = view.findViewById(R.id.txtName)
        enCal = view.findViewById(R.id.txtCalorie)
        enImage = view.findViewById(R.id.txtImage)
        var add = view.findViewById<Button>(R.id.submitFB)

        add.setOnClickListener{
            Toast.makeText(activity, "Successes Insert", Toast.LENGTH_SHORT)
            saveInsert()
        }

        recyclerView = requireView().findViewById(R.id.recyclerView)
        mAuth = FirebaseAuth.getInstance()
        db =
            FirebaseDatabase.getInstance("https://calculator-calorie-dfa67-default-rtdb.asia-southeast1.firebasedatabase.app/")
        recyclerView.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL, false)
        databaseReference = db.getReference("Calorie/")
        response = mutableListOf()
        adapter = AdapterDB(response as ArrayList<Food>)
        recyclerView.adapter = adapter
        onBindingFirebase()

    }

    private fun saveInsert() {
        val empName = enName.text.toString()
        val empCal  = enCal.text.toString()
        val empImage = enImage.text.toString()

        if (empName.isEmpty() && empCal.isEmpty() && empImage.isEmpty()){
            Toast.makeText(activity, "Input Empty", Toast.LENGTH_SHORT)
        }else {
            val lastId = maxId + 1
            val employee = Food(empImage, empCal, empName)

            databaseReference.child(lastId.toString()).setValue(employee)
                .addOnCompleteListener {
                    Toast.makeText(activity, "Succes Insert", Toast.LENGTH_SHORT)
                }.addOnFailureListener { err ->
                    Toast.makeText(activity, "Error ${err.message}", Toast.LENGTH_SHORT)
                }
        }
    }

    private fun onBindingFirebase() {
        databaseReference.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                response.add(snapshot.getValue(Food::class.java)!!)
                adapter!!.notifyDataSetChanged()
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                response
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {
                System.out.println("The read failed: " + error.getCode());
            }
        })
    }

    class AdapterDB(val dbList: List<Food>) : RecyclerView.Adapter<viewHolderDB>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolderDB {
            return viewHolderDB(LayoutInflater.from(parent.context)
                .inflate(R.layout.row, parent, false))
        }

        override fun onBindViewHolder(holder: viewHolderDB, position: Int) {
            val dtModel = dbList[position]
            holder.nameRow?.text = dtModel.foodName
            holder.cal?.text = dtModel.cal.toString()
            Picasso.get().load(dtModel.image)
                .into(holder.imageRow)
        }

        override fun getItemCount(): Int {
            return dbList.size
        }
    }

    class viewHolderDB(view: View) : RecyclerView.ViewHolder(view) {
        var imageRow: ImageView? = null
        var nameRow: TextView? = null
        var cal: TextView? = null

        init {
            imageRow = itemView.findViewById(R.id.foodImage) as ImageView
            nameRow = itemView.findViewById(R.id.nameView) as TextView
            cal = itemView.findViewById(R.id.foodDescription) as TextView

        }

    }

}