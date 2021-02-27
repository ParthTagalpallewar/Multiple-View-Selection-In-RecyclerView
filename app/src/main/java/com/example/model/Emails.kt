package com.example.model

class Emails {

    fun fakeEmails(): ArrayList<Email> {

        return arrayListOf(
                Email(
                        user = "Facebook",
                        subject = "Offering you a promotion",
                    stared = true,
                        preview = "congrates we love your work",
                        date = "5 jun"
                ),
                Email(
                        user = "Google",
                        subject = "Do you want to do job?",
                        preview = "We need developer like your",
                        date = "5 jun"
                ), Email(
                user = "Intershala",
                subject = "Can you train us the way we teach student",
                stared = true,
                preview = "we need your help",
                date = "5 jun"
        ), Email(
                user = "Book selling app",
                subject = "Thanku for making me i am do help to lot of peoples",
                preview = "welcome yar",
                date = "5 jun"
        ), Email(
                user = "Zomato",
                subject = "Sir do you want free sevice",
                preview = "No sorry",
                stared = true,
                date = "5 jun"
        ), Email(
                user = "Zomato",
                subject = "Sir can you teach us how to cook",
                preview = "No i am busk right now!!",
                date = "5 jun"
        ), Email(
                user = "FlipCart",
                subject = "Do you want our compony for free",
                stared = true,
                preview = "Yes but i will donate it to poor people",
                date = "5 jun"
        ), Email(
                user = "Acb",
                subject = "Um amigo quer que você curta uma Página dele",
                stared = true,
                preview = "Fulano convidou você para curtir a sua Página no Facebook",
                date = "5 jun"
        ), Email(
                user = "Hello",
                subject = "Um amigo quer que você curta uma Página dele",
                preview = "Fulano convidou você para curtir a sua Página no Facebook",
                date = "5 jun",
                stared = true,

        ), Email(
                user = "World",
                subject = "Um amigo quer que você curta uma Página dele",
                preview = "Fulano convidou você para curtir a sua Página no Facebook",
                date = "5 jun"
        )
        )
    }

}
