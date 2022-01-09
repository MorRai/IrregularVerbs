package com.rai.irregularverbs.data

class IrregularVerbsGenerator {
    companion object {
        fun getVerbs(): List<IrregularVerbs>{
            return listOf(
                IrregularVerbs(1, "be", "was/were", "been", "быть", "I want to be a happy woman.", "I was a fat child.", "I have been blessed.",1,0,0),
                IrregularVerbs(2, "become", "became", "become", "становиться", "He wants to become a football player.", "Bill won the lottery and became rich.", "Obesity has become a big problem.",1,0,0),
                IrregularVerbs(3, "begin", "began", "begun", "начать", "The alphabet begins with 'A'.", "It began to rain just as we were going out.", "They had begun to work when the bell ring",1,0,0),
                IrregularVerbs(4, "break", "broke", "broken", "сломить", "Be careful! Don't break the vase.", "The kid off the tree and broke his arm.", "We cant use our microwave because it is broken.",1,0,0),
                IrregularVerbs(5, "bring", "brought", "brought", "приносить", "Bring me the newspaper Buddy!", "Astronauts brought back a piece of the moon.", "They ate all the food we had brought for the party.",1,0,0),
                IrregularVerbs(6, "build", "built", "built", "строить", "They are building new schools.", "The survivors built a raft and were able to get to he shore.", "More and more hotels ara built for tourists in this region.",1,0,0),
                IrregularVerbs(7, "buy", "bought", "bought", "покупать", "", "", "",1,0,0),
                IrregularVerbs(51, "beat", "beat", "beaten", "бить", "Why did you beat me?", "Do you think you can beat Jack at chess?", "My team has been beaten four times already.",2,0,0),
                IrregularVerbs(52, "bend", "bent", "bent", "гнуть", "The wind bends the trees.", "The doctor bent over to examine the patient.", "Be careful! You have bent me glasses.",2,0,0),
                IrregularVerbs(53, "bite", "bit", "bit", "укусить", "The dog bites the woman.", "My neighbors dog bit Sally the other day.", "During the picnic, we were bitten by mosquitoes.",2,0,0),
                IrregularVerbs(54, "bleed", "bled", "bled", "кровоточить", "I have cut my hand it is bleeding.", "The injured dog bled to death on the roadside.", "Bill could have bled to death if the he had not received help.",2,0,0),
                IrregularVerbs(55, "blow", "blew", "blown", "дуть", "Blow the whistle!", "Billy blew the candles of his birthday cake.", "That terrible windstorm had blow all the threes down.",2,0,0),
                IrregularVerbs(56, "burn", "burnt", "burnt", "сжигать", "I will burn that letter after reading it!", "Susan burnt all the letters her boyfriend had written to her.", "The historical building was burnt to ashes by vandals.",2,0,0),
                IrregularVerbs(57, "burst", "burst", "burst", "лопнуть", "I hate balloons when they burst.", "One of our car tyres burst after hitting a pothole.", "My son started to cry because his balloon had burst.",2,0,0),
                IrregularVerbs(101, "bear", "bore", "born", "вынашвать", "I cannot bear her bad manners anymore.", "The child bore the pain without complaint while the doctor cured the injuries on his arm.", "The actor was born in a small village in Argentina.",3,0,0),
                IrregularVerbs(102, "beget", "begot", "begotten", "рождать", "Toxic gases beget air pollution.", "The moral virtues are the political offspring which flattery begot upon pride.", "It is born of causes cognate with  those which have begotten frugality.",3,0,0),
                IrregularVerbs(103, "bet", "bet", "bet", "держать пари", "I think you shouldn't bet with me cause i already won. ", "Mary bet a lot of money on that horse that came in last.", "John has bet ten dollars that he can beat me in a tennis match.",3,0,0),
                IrregularVerbs(104, "bid", "bid", "bid", "предложить цену", "The collectors will be bidding for the masterpiece.", "At the auction, james bid $5,000 for the painting.", "Bill has bid the highest price for the book at the auction so far.",3,0,0),
                IrregularVerbs(105, "bide", "bode", "bidden", "выжидать", "Like any good mystery novel detective, he should bide his time.", "Argyll allowed the committee of Estates to rule, as before, and bode his time.", "I have bidden my time to make my move.",3,0,0),
                IrregularVerbs(106, "bind", "bound", "bound", "связвать", "You can use this to bind your notes.", "I've got Shakespeare's Complete Works bound in leather.", "A strong culture has bound the Chinese people together for many years.",3,0,0),
                IrregularVerbs(107, "breed", "bred", "bred", "разводить", "", "", "",3,0,0)

            )
        }
    }
}