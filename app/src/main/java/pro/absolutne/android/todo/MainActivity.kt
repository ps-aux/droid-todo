package pro.absolutne.android.todo

import android.support.design.widget.BaseTransientBottomBar
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.main_activity.*
import pro.absolutne.android.todo.entity.TodoItem
import pro.absolutne.android.todo.service.TodoItemService
import pro.absolutne.android.todo.view.TodoAdapter
import timber.log.Timber
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var service: TodoItemService

    private lateinit var layoutMan: RecyclerView.LayoutManager

    private var toBeDeleted: TodoItem? = null

    fun execPendingDeletion() {
        log("Executing pending deletion")
        toBeDeleted?.let {
            service.delete(toBeDeleted!!)
        }
    }

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        log("Starting")
        Injectors.getAppInjector().inject(this)

        setContentView(R.layout.main_activity)

        layoutMan = LinearLayoutManager(this)
        listview.layoutManager = layoutMan

        val adap = TodoAdapter(mutableListOf(), onDelete = {
            // Execute the previous possibl epending deletion
            execPendingDeletion()
            toBeDeleted = it

            val snackbar = Snackbar
                    .make(main_content, "Welcome to AndroidHive", Snackbar.LENGTH_LONG)

            snackbar.duration = Snackbar.LENGTH_LONG
            snackbar.addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {

                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    log("Dismissed---------")
                    execPendingDeletion()
                }
            })

            snackbar.show()
        })

        listview.adapter = adap

        add_todo.clicks().subscribe {
            log("button clicked")
            service.insert(TodoItem("new todo item" + c++),
                    { log("inserted") })
        }

        load(adap)
    }

    override fun onPause() {
        log("We will be pause")
        super.onPause()
    }

    override fun onStop() {
        log("We will be stopped")
        super.onDestroy()
    }

    override fun onDestroy() {
        log("We will be destroyed")
        super.onDestroy()
    }

    var c = 0

    fun log(str: String) {
        Timber.tag("--xx--")
        Timber.i(str)
    }

    fun load(a: TodoAdapter) = loadTodo {
        log("Loaded todo" + it.toString())
        a.setNewItems(it)
    }

    fun loadTodo(success: (List<TodoItem>) -> Unit) =
            service.findAll(
                    success,
                    { log("error" + it) },
                    { log("Finished") })

}
