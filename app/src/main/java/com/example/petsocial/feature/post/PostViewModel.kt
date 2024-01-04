package com.example.petsocial.feature.post

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postRepository: PostRepositoryImpl
):ViewModel() {
    private val _loadingState = MutableStateFlow<Boolean?>(null)
    val loadingState: StateFlow<Boolean?> get() = _loadingState
    private val _errorString = MutableStateFlow<String?>(null)
    val errorString: StateFlow<String?> get() = _errorString

    private val _post = MutableStateFlow<Boolean?>(null)
    val post: StateFlow<Boolean?> get() = _post

    private val _dogVaccineOptions = MutableStateFlow<List<String>>(listOf())
    val dogVaccineOptions: StateFlow<List<String>> get() = _dogVaccineOptions
    private val _catVaccineOptions = MutableStateFlow<List<String>>(listOf())
    val catVaccineOptions: StateFlow<List<String>> get() = _catVaccineOptions
    private val _dogGeniusOptions = MutableStateFlow<List<String>>(listOf())
    val dogGeniusOptions: StateFlow<List<String>> get() = _dogGeniusOptions
    private val _catGeniusOptions = MutableStateFlow<List<String>>(listOf())
    val catGeniusOptions: StateFlow<List<String>> get() = _catGeniusOptions
    init {
        catGeniusOptions()
        vaccineCatSpinnerList()
        vaccineDogSpinnerList()
        dogGeniusList()
    }

    fun postSave(postData: PostData){
        _loadingState.value=true
        CoroutineScope(Dispatchers.IO).launch {
            val createPost=postRepository.savePost(postData)
            _post.value=createPost.data
        }
    }

    private fun catGeniusOptions(){
        val catsGeniusList = arrayListOf(
            "Tekir",
            "İran Kedisi",
            "Sarman",
            "Smokin",
            "Van Kedisi",
            "Ankara Kedisi",
            "İran Kedisi",
            "Bombay Kedisi",
            "American Shorthair",
            "Maine Coon",
            "Scottish Fold",
            "Bengal",
            "Sphynx",
            "Rus Mavisi",
            "British Shorthair",
            "Abyssinian",
            "Siamese"
        )
        _catGeniusOptions.value = catsGeniusList
    }
    private fun dogGeniusList(){
        val dogGeniusList = arrayListOf(
            "Pug",
            "Chihuahua",
            "Çov-çov",
            "Altın Retriever",
            "Labrador Retriever",
            "Fransız Bulldog",
            "Sibirya Huskisi",
            "Pomeranian",
            "Cavalier King Charles Spaniel",
            "Yorkshire Terrier",
            "German Shepherd",
            "Dachshund",
            "Bulldog"
        )
        _dogGeniusOptions.value = dogGeniusList
    }

    private fun vaccineCatSpinnerList(){
        val vaccinationList = arrayListOf(
            "İç-Dış Parazit",
            "Karma Aşı",
            "Lösemi",
            "Mantar",
            "Kuduz Aşısı",
        )
        _catVaccineOptions.value = vaccinationList
    }
    private fun vaccineDogSpinnerList(){
        val vaccinationList = arrayListOf(
            "İç-Dış Parazit",
            "Karma Aşı",
            "Lösemi",
            "Mantar",
            "Kuduz Aşısı",
            "Bronşit Aşısı",
            "Corona Aşısı",
        )
        _dogVaccineOptions.value = vaccinationList
    }
}