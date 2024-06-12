
package com.example.proyecto_si

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        commentEditText = findViewById(R.id.commentEditText)
        postCommentButton = findViewById(R.id.postCommentButton)
        commentsRecyclerView = findViewById(R.id.commentsRecyclerView)

        commentsList = mutableListOf()
        commentsAdapter = CommentsAdapter(commentsList)
        commentsRecyclerView.layoutManager = LinearLayoutManager(this)
        commentsRecyclerView.adapter = commentsAdapter

        database = FirebaseDatabase.getInstance().reference.child("comments")
        auth = FirebaseAuth.getInstance()

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
        // Split the email address using "@" as delimiter and get the first part
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
                // Handle database error
            }
        })
    }
}
