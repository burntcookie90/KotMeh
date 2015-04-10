package io.dwak.meh.model

class Deal (
        val feautures : String,
        val id : String,
        val items : List<Item>,
        val photos : List<String>,
        val title : String,
        val soldOutAt : String,
        val specifications : String,
        val story : Story,
        val theme : Theme,
        val url : String,
        val topic : Topic) {

}