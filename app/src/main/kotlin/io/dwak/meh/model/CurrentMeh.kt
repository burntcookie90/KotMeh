package io.dwak.meh.model

import java.util.ArrayList

public class CurrentMeh (
        private val deal : Deal,
        private val poll : Poll,
        private val video : Video) {

}

class Deal (
        private val feautures : String,
        private val id : String,
        private val items : List<Item>,
        private val photos : List<String>,
        private val title : String,
        private val soldOutAt : String,
        private val specifications : String,
        private val story : Story,
        private val theme : Theme,
        private val url : String,
        private val topic : Topic) {

}

class Topic(
        private val commentCount : Int,
        private val createdAt : String,
        private val id : String,
        private val replyCount : Int,
        private val url : String,
        private val voteCount : Int) {

}

class Theme(
        private val accentColor : String,
        private val foreground : String,
        private val backgroundColor : String,
        private val backgroundImage : String) {

}

class Story(
        private val title : String,
        private val body : String) {

}

class Item(
        private val attributes : List<Any>,
        private val condition : String,
        private val id : String,
        private val price : Int,
        private val photo : String) {

}

class Poll(
        private val answers : List<Answer>,
        private val id : String,
        private val startDate : String,
        private val title : String,
        private val topic : Topic) {

}

class Answer(
        private val id : String,
        private val text : String,
        private val voteCount : Int) {

}

class Video(
        private val id : String,
        private val startDate : String,
        private val title : String,
        private val url : String,
        private val topic : Topic) {

}
