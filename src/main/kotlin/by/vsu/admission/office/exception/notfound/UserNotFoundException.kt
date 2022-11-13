package by.vsu.admission.office.exception.notfound

import com.epam.carapi.exception.notfound.NotFoundException


class UserNotFoundException : NotFoundException {
    constructor(id: Long): super("User", id)
    constructor(username: String): super("User with username = $username not found.")
}