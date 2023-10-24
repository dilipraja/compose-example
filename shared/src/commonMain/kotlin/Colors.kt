import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

open class Colors {
    companion object {
        @get:Composable
        val Colors.maroon: Color
            get() = Color(0xFF88022B)

        @get:Composable
        val Colors.yellow: Color
            get() = Color(0xFFFFC107)

        @get:Composable
        val Colors.homeBg: Color
            get() = Color(0xFFBDAAE)

        @get:Composable
        val Colors.blue: Color
            get() = Color(0xFF32004E)

        @get:Composable
        val Colors.gray: Color
            get() = Color(0xFF424242)

        @get:Composable
        val Colors.dialogColor: Color
            get() = Color(0xFF356ED5)

        @get:Composable
        val Colors.lightMaroon: Color
            get() = Color(0xFFFFC5D7)

    }
}