package io.dwak.meh

import android.os.Bundle
import android.support.v7.app.ActionBarActivity
import android.widget.TextView
import butterknife.bindView
import io.dwak.meh.network.MehService
import retrofit.RestAdapter


open class MainActivity : ActionBarActivity(), MainView {
    val titleView : TextView by bindView(R.id.title)
    private var mehService : MehService? = null

    override fun populatePage() {
        throw UnsupportedOperationException()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super<ActionBarActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mehService = RestAdapter.Builder()
                .setEndpoint(MehService.ENDPOINT_URL + MehService.ENDPOINT_VERSION)
                .build().create(javaClass<MehService>());
    }
}
