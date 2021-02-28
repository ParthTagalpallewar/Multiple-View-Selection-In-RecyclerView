package com.example.multiselectinrecyclerview.model

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
                subject = "Do Your want intership",
                stared = true,
                preview = "Join Intershala",
                date = "5 jun"
        ), Email(
                user = "Edeoda",
                subject = "Join the learn the AWS for free",
                preview = "Join fast to start class",
                date = "5 jun"
        ), Email(
                user = "Zomato",
                subject = "Your delivery is ready",
                preview = "Soon it will reach",
                stared = true,
                date = "5 jun"
        ), Email(
                user = "Udemy",
                subject = "Join our cource now",
                preview = "No i am busk right now!!",
                date = "5 jun"
        ), Email(
                user = "FlipCart",
                subject = "Your order is delivered",
                stared = true,
                preview = "Thanku for ordering ",
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
