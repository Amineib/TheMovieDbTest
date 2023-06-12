package io.dvlt.themoviedbtest.data.exception

import java.io.IOException

// Custom exception for network-related errors
class NetworkException(message: String, cause: Throwable) : IOException(message, cause)

// Custom exception for API-related errors
class ApiException(message: String, cause: Throwable) : RuntimeException(message, cause)

// Custom exception for general repository errors
class RepositoryException(message: String, cause: Throwable) : RuntimeException(message, cause)
