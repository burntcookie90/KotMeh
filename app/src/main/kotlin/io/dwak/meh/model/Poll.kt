package io.dwak.meh.model

class Poll(
        val answers : List<Answer>,
        val id : String,
        val startDate : String,
        val title : String,
        val topic : Topic) {

}