package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PrethistaList(
    @SerialName("PM_Code")
    val PM_Code: Int,
    @SerialName("PM_Name")
    val PM_Name: String,
    @SerialName("PM_NameEng")
    val PM_NameEng: String,
    @SerialName("imgurl")
    val imgurl: String)