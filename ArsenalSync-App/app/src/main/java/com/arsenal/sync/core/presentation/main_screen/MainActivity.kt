package com.arsenal.sync.core.presentation.main_screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.arsenal.sync.core.data.Preferences
import com.arsenal.sync.core.domain.utils.ThemeOption
import com.arsenal.sync.core.presentation.navigation.RootNavGraph
import com.arsenal.sync.core.presentation.utils.Utils.getStartDestination
import com.arsenal.sync.theme.ArsenalSyncTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isReady = MutableStateFlow<Boolean?>(null)
        val themeState = MutableStateFlow(ThemeOption.SYSTEM)


        lifecycleScope.launch {
            coroutineScope {
                launch(Dispatchers.Default) {
                    isReady.value = preferences.getIsOnboarded()
                }
                launch(Dispatchers.IO) {
                    preferences.getTheme().collect {
                        themeState.value = it
                    }
                }
            }
        }

        val splashScreen = installSplashScreen()

        splashScreen.setKeepOnScreenCondition { isReady.value == null }
        enableEdgeToEdge()
        setContent {
            val readyState by isReady.collectAsStateWithLifecycle()
            val theme by themeState.collectAsStateWithLifecycle()
            readyState?.let { ready ->
                val rootNavController = rememberNavController()

                ArsenalSyncTheme(themeOption = theme) {
                    RootNavGraph(
                        rootNavController = rootNavController,
                        startDestination = getStartDestination(ready),
                        onBackOrFinish = { handleBackClick(rootNavController) }
                    )
                }
            }
        }
    }

    private fun handleBackClick(rootNavController: NavHostController) {
        if (rootNavController.previousBackStackEntry == null) finish()
        else rootNavController.navigateUp()
    }
}
