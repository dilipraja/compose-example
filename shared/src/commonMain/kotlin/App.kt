import Colors.Companion.homeBg
import Colors.Companion.maroon
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.launch
import model.PrethistaList

@Composable
fun App() {
    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            ScaffoldExample()
        }

    }
}


@Composable
fun Drawer() {
    // Column Composable
    Column(
        Modifier
            .background(MaterialTheme.colors.maroon)
            .fillMaxSize()
    ) {
        // Repeat is a loop which
        // takes count as argument
        repeat(5) { item ->
            Text(text = "Item number $item", modifier = Modifier.padding(8.dp), color = Color.White)
        }
    }
}

@Composable
fun prethistaPage(viewModel: PrethistaViewModel){
    val uiState by viewModel.uiState.collectAsState()
    AnimatedVisibility(uiState.prethistaList.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.fillMaxSize().padding(horizontal = 5.dp),
                content = {
                    items(uiState.prethistaList) {
                        Prethistacell(it)
                    }
                }

            )
    }

}
@Composable
fun Prethistacell(prethistaList: PrethistaList){
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,

    ) {
        KamelImage(
            asyncPainterResource(prethistaList.imgurl), prethistaList.PM_NameEng,
            contentScale = ContentScale.Crop,
            modifier = Modifier.width(150.dp).height(150.dp).clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
        )

        Text(
            text = prethistaList.PM_NameEng,
            color = Color.Red,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)
        )
    }

}

@Composable
fun Body() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize().padding(top = 10.dp)
            .background(MaterialTheme.colors.homeBg)
    ) {
        val prethistaViewModel = getViewModel(Unit, viewModelFactory { PrethistaViewModel() })
        prethistaPage(prethistaViewModel)
    }
}

@Composable
fun ScaffoldExample() {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val coroutineScope = rememberCoroutineScope()

    // Scaffold Composable
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                 onMenuClicked = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                })
        },
        content = {
            Body()
        },

        drawerContent = {
            Drawer()
        },

    )
}

@Composable
fun TopBar(onMenuClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "Sree Ayyappan\nGuruvaurappn Temple",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(end = 30.dp)
            )
        },
        modifier = Modifier.height(70.dp),
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                // When clicked trigger onClick
                // Callback to trigger drawer open
                modifier = Modifier.clickable {}.padding(horizontal = 10.dp),
                tint = Color.White
            )
        },
        backgroundColor = MaterialTheme.colors.maroon
    )
}

expect fun getPlatformName(): String