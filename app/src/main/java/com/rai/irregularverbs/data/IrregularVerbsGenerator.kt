package com.rai.irregularverbs.data

class IrregularVerbsGenerator {
    companion object {
        fun getVerbs(): List<IrregularVerbs>{
            return listOf(
                IrregularVerbs(1, "be", "was/were", "been", "быть", "I want to be a happy woman.", "I was a fat child.", "I have been blessed.", "",1,0,0),
                IrregularVerbs(2, "become", "became", "become", "становиться", "He wants to become a football player.", "Bill won the lottery and became rich.", "Obesity has become a big problem.", "",1,0,0),
                IrregularVerbs(3, "begin", "began", "begun", "начать", "Thealphabet begins with 'A'.", "It began to rain just as we were going out.", "They had begun to work when the bell ring", "",1,0,0),
                IrregularVerbs(4, "break", "broke", "broken", "сломить", "Be careful! Dont break the vase.", "The kid off the tree and broke his arm.", "We cant use our mecrowave because it is broken.", "",1,0,0),
                IrregularVerbs(5, "bring", "brought", "brought", "приносить", "Bring me the newspaper Buddy!", "Astronauts brought back a piece of the moon.", "They ate all the food we had brought for the party.", "",1,0,0),
                IrregularVerbs(6, "build", "built", "built", "строить", "They are building new schools.", "The survivors built a raft and were able to get to he shore.", "More and more hotels ara built for tourists in this region", "",1,0,0),
                IrregularVerbs(7, "buy", "bought", "bought", "покупать", "", "", "", "",1,0,0),
                IrregularVerbs(51, "beat", "beat", "beaten", "бить", "Why did you beat me?", "Do you think you can beat Jack at chesse?", "My team has been beaten four times alredy.", "",2,0,0),
                IrregularVerbs(101, "bear", "bore", "born", "вынашвать", "I cannot bear her bad manners anymore.", "The child bore the pain without complaint while the doctor cured the injuries on his arm.", "The actor was born in a small village in Argentina.", "",3,0,0)
            )
        }
    }
}