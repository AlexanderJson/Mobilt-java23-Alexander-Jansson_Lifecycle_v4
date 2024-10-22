package com.example.inl3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.inl3.db_logic.User

class UserAdapter(private var userList: MutableList<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val username: TextView = itemView.findViewById(R.id.username)
        val password: TextView = itemView.findViewById(R.id.password)
        val email: TextView = itemView.findViewById(R.id.email)
        val fName: TextView = itemView.findViewById(R.id.firstName)
        val lName: TextView = itemView.findViewById(R.id.lastName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.username.text = user.username
        holder.password.text = user.password
        holder.email.text = user.email
        holder.fName.text = user.firstName
        holder.lName.text = user.lastName
    }

    fun updateUsers(newUsers: List<User>) {

        for (i in newUsers.indices) {
            if (i >= userList.size) {
                userList.add(newUsers[i])
                notifyItemInserted(i)
            } else {
                userList[i] = newUsers[i]
                notifyItemChanged(i)
            }
        }
        if (userList.size > newUsers.size) {
            val removedUsers = userList.size - newUsers.size
            for (i in 0 until removedUsers) {
                userList.removeAt(userList.size - 1)
                notifyItemRemoved(userList.size)
            }
        }
    }
}