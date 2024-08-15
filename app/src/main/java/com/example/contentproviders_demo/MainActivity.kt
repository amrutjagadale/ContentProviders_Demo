package com.example.contentproviders_demo

import android.content.ContentResolver
import android.content.ContentValues
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getSMS()
        Log.e("tag","-------------------------------------------")

        getCallLog()
        Log.e("tag","-------------------------------------------")

        getSystemSettings()

        Log.e("tag","-------------------------------------------")
        addTask(103,"Assignments task",2,false)
        addTask(104,"complete task",3,false)
        addTask(105,"complete task in time",4,true)
        addTask(106,"Meeting",5,true)

        Log.e("tag","-------------------------------------------")

        getAllTask()
        Log.e("tag","-------------------------------------------")

    }
    private fun addTask(id : Int, title : String, priority : Int, isComplete : Boolean){
        var contentUri = Uri.parse("content://in.bitcode.contents")
        contentUri = Uri.withAppendedPath(contentUri,"task")
        val values = ContentValues()
        values.put("id",taskId)
        values.put("task_title",title)
        values.put("task_prioroty",priority)
        values.put("task_completion",isComplete)

        val uriToNewTask = contentResolver.insert(contentUri,values)

        Log.e("tag","${uriToNewTask.toString()}")
        val c = contentResolver.query(uriToNewTask!!,null,null,null)

        while (c!!.moveToNext()){
            Log.e("tag","${c.getInt(0)}")
        }
        c.close()
    }
    private fun getAllTask(){
        var contentUri = Uri.parse("content://in.bitcode.contents")
        contentUri = Uri.withAppendedPath(contentUri,"task")
        val c = contentResolver.query(contentUri,null,null,null)
        while (c!!.moveToNext()){
            Log.e("tag","${c.getInt(0)} -- ${c.getString(1)} -- ${c.getInt(2)} -- ${c.getString(3)}")
        }
        c.close()
    }

    private fun getSMS(){
        val contentUri = Uri.parse("content://sms")
        val Smscursor = contentResolver.query(contentUri,null,null,null)
        for (eachColumn in Smscursor!!.columnNames){
            Log.e("tag","$eachColumn")
        }
        Smscursor.close()
    }
    private fun getCallLog(){
        val ContentUri = android.provider.CallLog.Calls.CONTENT_URI
        val c = contentResolver.query(ContentUri,
            null,
            null,
            null)

        for (eachColumn in c!!.columnNames){
            Log.e("tag","$eachColumn")
        }
        c.close()
    }
    private fun getSystemSettings(){
        val ContentUri = android.provider.Settings.System.CONTENT_URI
        var c = contentResolver.query(ContentUri,
            null,
            null,
            null)

        for (eachColumn in c!!.columnNames){
            Log.e("tag","$eachColumn")
        }
        c.close()
    }
}