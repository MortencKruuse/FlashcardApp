package com.example.flashcardapp.domain.exceptions



/**
 * The Exception base class comes from the Java standard library
 * and Java doesn't have default parameters
 * so the Java class must have four constructors for every combination of acceptable inputs.
 * Furthermore, both message and cause are allowed to be null in Exception
 * so ours should be, too, if we're trying to be 100% compatible with Exception.
 * SOURCE: https://stackoverflow.com/a/68775013
 */
class DomainException(message: String? = null, cause: Throwable? = null) : Exception(message, cause),
    IDomainException {
    constructor(cause: Throwable) : this(null, cause)

}
