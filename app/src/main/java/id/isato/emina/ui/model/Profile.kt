package id.isato.emina.ui.model

data class Profile(
    val userName: String,
    val occupation: String,
    val avatar: String,
    val bio: String,
    val backdrop: String
) {

    companion object {
        fun mock() = Profile(
            userName = "Iman",
            occupation = "Android Engineer",
            avatar = "https://www.neverendingvoyage.com/wp-content/uploads/2017/11/takayama-street.jpg",
            bio = "Hi! I'm Iman, working as Android Developer. Enthusiast in creating app from scratch using the latest technology.",
            backdrop = "https://www.neverendingvoyage.com/wp-content/uploads/2017/11/takayama-street.jpg"
        )
    }

}
