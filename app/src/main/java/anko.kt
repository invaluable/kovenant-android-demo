package nl.mplatvoet.komponents.kovenant.android.demo

import android.app.Activity
import android.os.Bundle
import org.jetbrains.anko.*

/**
 * Created by tcheng on 3/23/16.
 */

class AnkoTestActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AnkoTestActicityUI().setContentView(this)
    }
}

class AnkoTestActicityUI : AnkoComponent<AnkoTestActivity> {
    override fun createView(ui: AnkoContext<AnkoTestActivity>) = with(ui) {
        linearLayout {
            val name = editText()
            button("Say Hello") {
                onClick { ctx.toast("Hello, ${name.text}!") }
            }
        }
    }
}
