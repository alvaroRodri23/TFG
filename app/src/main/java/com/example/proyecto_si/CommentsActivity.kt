
package com.example.proyecto_si

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_si.Comment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class CommentsActivity : AppCompatActivity() {

    private lateinit var commentEditText: EditText
    private lateinit var postCommentButton: Button
    private lateinit var commentsRecyclerView: RecyclerView
    private lateinit var commentsAdapter: CommentsAdapter
    private lateinit var commentsList: MutableList<Comment>
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var botonPerfil: ImageButton
    private lateinit var botonHome: ImageButton
    private lateinit var botonAnaliticas: ImageButton
    private lateinit var botonforo: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)


        botonPerfil = findViewById(R.id.botonperfil)
        botonHome = findViewById(R.id.botonhome)
        botonAnaliticas = findViewById(R.id.botonanaliticas)
        botonforo = findViewById(R.id.botonforo)

        commentEditText = findViewById(R.id.commentEditText)
        postCommentButton = findViewById(R.id.postCommentButton)
        commentsRecyclerView = findViewById(R.id.commentsRecyclerView)

        commentsList = mutableListOf()
        commentsAdapter = CommentsAdapter(commentsList)
        commentsRecyclerView.layoutManager = LinearLayoutManager(this)
        commentsRecyclerView.adapter = commentsAdapter

        database = FirebaseDatabase.getInstance().reference.child("comments")
        auth = FirebaseAuth.getInstance()


        botonPerfil.setOnClickListener {
            val intent = Intent(this, perfilactivity::class.java)
            startActivity(intent)
        }

        botonHome.setOnClickListener {
            val intent = Intent(this, principal2::class.java)
            startActivity(intent)
        }

        botonAnaliticas.setOnClickListener {
            val intent = Intent(this, AnalyticsActivity::class.java)
            startActivity(intent)
        }
        botonforo.setOnClickListener {
            val intent = Intent(this, CommentsActivity::class.java)
            startActivity(intent)
        }

        postCommentButton.setOnClickListener {
            postComment()
        }

        loadComments()
    }

    private fun postComment() {
        val commentText = commentEditText.text.toString().trim()
        val currentUser = auth.currentUser

        if (commentText.isNotEmpty() && currentUser != null) {
            val username = getUsernameFromEmail(currentUser.email ?: "")
            val commentId = database.push().key
            val comment = Comment(
                userId = currentUser.uid,
                userName = username,
                commentText = commentText
            )

            if (commentId != null) {
                database.child(commentId).setValue(comment).addOnCompleteListener {
                    if (it.isSuccessful) {
                        commentEditText.text.clear()
                    }
                }
            }
        }
    }
    private fun getUsernameFromEmail(email: String): String {

        val parts = email.split("@")
        return parts[0]
    }

    private fun loadComments() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                commentsList.clear()
                for (commentSnapshot in snapshot.children) {
                    val comment = commentSnapshot.getValue(Comment::class.java)
                    if (comment != null) {
                        commentsList.add(comment)
                    }
                }
                commentsAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}
