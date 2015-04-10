package io.dwak.meh.model

import java.util.ArrayList

public class CurrentMeh (
        val deal : Deal,
        val poll : Poll,
        val video : Video) {

}

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

class Topic(
        val commentCount : Int,
        val createdAt : String,
        val id : String,
        val replyCount : Int,
        val url : String,
        val voteCount : Int) {

}

class Theme(
        val accentColor : String,
        val foreground : String,
        val backgroundColor : String,
        val backgroundImage : String) {

}

class Story(
        val title : String,
        val body : String) {

}

class Item(
        val attributes : List<Any>,
        val condition : String,
        val id : String,
        val price : Int,
        val photo : String) {

}

class Poll(
        val answers : List<Answer>,
        val id : String,
        val startDate : String,
        val title : String,
        val topic : Topic) {

}

class Answer(
        val id : String,
        val text : String,
        val voteCount : Int) {

}

class Video(
        val id : String,
        val startDate : String,
        val title : String,
        val url : String,
        val topic : Topic) {

}
