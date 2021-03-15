package barbarich.ilya.proplayer

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import barbarich.ilya.proplayer.network.PlayerApi
import barbarich.ilya.proplayer.network.model.PlayerFilter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("barbarich.ilya.proplayer", appContext.packageName)
    }


    @Test
    fun getDataFromRetrofit() {
        val scope = CoroutineScope(Dispatchers.Main + Job())
        scope.launch {
            val getPropertiesDeferred = PlayerApi.retrofitService.getDataFromApi(PlayerFilter.SORT_BY_NAME.value)
        }
    }
}