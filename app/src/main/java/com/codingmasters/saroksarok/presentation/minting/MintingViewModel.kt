package com.codingmasters.saroksarok.presentation.minting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingmasters.saroksarok.domain.repository.BaseRepository
import com.codingmasters.saroksarok.extension.AllState
import com.codingmasters.saroksarok.extension.MakeListState
import com.codingmasters.saroksarok.extension.MintingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import timber.log.Timber
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class MintingViewModel @Inject constructor(
    private val baseRepository: BaseRepository
):ViewModel() {
    private val _mintingState = MutableStateFlow<MintingState>(MintingState.Loading)
    val mintingState: StateFlow<MintingState> = _mintingState.asStateFlow()

    private val _makeListState = MutableStateFlow<MakeListState>(MakeListState.Loading)
    val makeListState: StateFlow<MakeListState> = _makeListState.asStateFlow()


    private val walletAddress = "0xc6D5B077365EBee96A8607aFD14D827a0d2B9dB3"

    fun minting(file:MultipartBody.Part, title:String, description:String, name:String){
        viewModelScope.launch {
            baseRepository.minting(file, title, description, name, walletAddress).onSuccess {response->
                _mintingState.value=MintingState.Success(response)
            }.onFailure {
                _mintingState.value=MintingState.Error("minting state error!!")
                if (it is HttpException) {
                    try {
                        val errorBody: ResponseBody? = it.response()?.errorBody()
                        val errorBodyString = errorBody?.string() ?: ""
                        httpError(errorBodyString)
                    } catch (e: Exception) {
                        // JSON 파싱 실패 시 로깅
                        Timber.e("Error parsing error body: ${e}")
                    }
                }
            }
        }
    }

    fun makeList(tokenId:Int){
        viewModelScope.launch {
            baseRepository.makeList(tokenId, BigDecimal("0.0001")).onSuccess {response->
                _makeListState.value=MakeListState.Success(response)
            }.onFailure {
                _makeListState.value=MakeListState.Error("make list error!!")
                if (it is HttpException) {
                    try {
                        val errorBody: ResponseBody? = it.response()?.errorBody()
                        val errorBodyString = errorBody?.string() ?: ""
                        httpError(errorBodyString)
                    } catch (e: Exception) {
                        // JSON 파싱 실패 시 로깅
                        Timber.e("Error parsing error body: ${e}")
                    }
                }
            }
        }
    }

    private fun httpError(errorBody: String) {
        // 전체 에러 바디를 로깅하여 디버깅
        Log.e("searchViewModel", "Full error body: $errorBody")

        // JSONObject를 사용하여 메시지 추출
        val jsonObject = JSONObject(errorBody)
        val errorMessage = jsonObject.optString("message", "Unknown error")

        // 추출된 에러 메시지 로깅
        Log.e("searchViewModel", "Error message: $errorMessage")
    }

}