package com.aio.kotlin.studylist.builder

class SimpleBuilder {

    private var teamName = ""
    private var forward = ""
    private var midfielder = ""
    private var winger = ""
    private var defender = ""

    class Builder(
        val teamName: String
    ) {

        var forward = ""
        var midfielder = ""
        var winger = ""
        var defender = ""

        fun forward(forward: String): Builder {
            this.forward = forward
            return this
        }
        fun midfielder(midfielder: String): Builder {
            this.midfielder = midfielder
            return this
        }
        fun winger(winger: String): Builder {
            this.winger = winger
            return this
        }
        fun defender(defender: String): Builder {
            this.defender = defender
            return this
        }

        fun build() : SimpleBuilder {
            return SimpleBuilder(this)
        }
    }

    constructor(builder: Builder) {
        teamName = builder.teamName
        forward = builder.forward
        midfielder = builder.midfielder
        winger = builder.winger
        defender = builder.defender
    }
}