import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.PrethistaList

data  class PrethistaUiState(
    val prethistaList: List<PrethistaList> = emptyList()
)
class PrethistaViewModel: ViewModel() {

    private val _uiState = MutableStateFlow<PrethistaUiState>(PrethistaUiState())
    val uiState = _uiState.asStateFlow()

    private val httpClient = HttpClient{
        install(ContentNegotiation){
            json()
        }
    }

    init {
        updatePrethista()
    }

    override fun onCleared() {
        httpClient.close()
    }
    fun updatePrethista(){
        viewModelScope.launch {
          val list =  getPrethistaList()
            _uiState.update {
                it.copy(list)
            }
        }
    }
    private suspend fun getPrethistaList(): List<PrethistaList> {
        lateinit var list: List<PrethistaList>
        try {
            list = httpClient.get("https://testios.suvidham.in/app/mbleserviceApp.asmx/ViewPrethista").
            body<List<PrethistaList>>()
            println("meals 1::")
        } catch (e: NoTransformationFoundException) {
            val listString = httpClient.get("https://testios.suvidham.in/app/mbleserviceApp.asmx/ViewPrethista").
            body<String>()

            //val postString = httpClient.post("")
            val json = kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
            }
            list = json.decodeFromString(listString)
            println("meals 2::")
        } finally {
            println("Meals 3:: ${list?.get(0)?.imgurl}")
        }

        return list
    }

}